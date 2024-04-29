package net.wareload.mapper.oidc

import org.keycloak.models.ClientSessionContext
import org.keycloak.models.KeycloakSession
import org.keycloak.models.ProtocolMapperModel
import org.keycloak.models.UserSessionModel
import org.keycloak.protocol.oidc.mappers.*
import org.keycloak.provider.ProviderConfigProperty
import org.keycloak.representations.IDToken

class CustomProtocolMapper : AbstractOIDCProtocolMapper(), OIDCAccessTokenMapper, OIDCIDTokenMapper,
    UserInfoTokenMapper {

    companion object {
        const val PROVIDER_ID = "custom-protocol-mapper"
        const val DISPLAY_CATEGORY = "Token Mapper"
        const val DISPLAY_TYPE = "Custom Token Mapper"
        const val HELP_TEXT = "Adds a custom text to the claim"
        private val list = IntArray(100) { 10 * (it + 1) }
        private var pointer = 0;
        private val configProperties = mutableListOf<ProviderConfigProperty>().apply {
            OIDCAttributeMapperHelper.addTokenClaimNameConfig(this)
            OIDCAttributeMapperHelper.addIncludeInTokensConfig(this, CustomProtocolMapper::class.java)
        }
    }

    override fun getDisplayCategory(): String {
        return DISPLAY_CATEGORY
    }

    override fun getDisplayType(): String {
        return DISPLAY_TYPE
    }

    override fun getHelpText(): String {
        return HELP_TEXT
    }

    override fun getConfigProperties(): List<ProviderConfigProperty> {
        return CustomProtocolMapper.configProperties
    }

    override fun getId(): String {
        return PROVIDER_ID
    }

    override fun setClaim(
        token: IDToken,
        mappingModel: ProtocolMapperModel,
        userSession: UserSessionModel,
        keycloakSession: KeycloakSession,
        clientSessionCtx: ClientSessionContext
    ) {
        val clamName = mappingModel.config.get("claim.name")
        val otherClaims = token.otherClaims[clamName]
        val map = mutableMapOf("value" + list[pointer].toString() to list[pointer].toString())
        if (otherClaims != null && otherClaims is Map<*, *>) {
            for ((key, value) in otherClaims.entries) {
                map[key.toString()] = value.toString()
            }
        }
        pointer += 1
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, map)
    }
}

