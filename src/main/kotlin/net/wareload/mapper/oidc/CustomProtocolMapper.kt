package net.wareload.mapper.oidc

import org.keycloak.models.ClientSessionContext
import org.keycloak.models.KeycloakSession
import org.keycloak.models.ProtocolMapperModel
import org.keycloak.models.UserSessionModel
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper
import org.keycloak.provider.ProviderConfigProperty
import org.keycloak.representations.IDToken

class CustomProtocolMapper : AbstractOIDCProtocolMapper(), OIDCAccessTokenMapper,
OIDCIDTokenMapper, UserInfoTokenMapper {

    companion object {
        const val PROVIDER_ID = "custom-protocol-mapper"
        private val configProperties = mutableListOf<ProviderConfigProperty>().apply {
            OIDCAttributeMapperHelper.addTokenClaimNameConfig(this)
            OIDCAttributeMapperHelper.addIncludeInTokensConfig(this, CustomProtocolMapper::class.java)
        }
    }

    override fun getDisplayCategory(): String {
        return "Token Mapper"
    }

    override fun getDisplayType(): String {
        return "Custom Token Mapper"
    }

    override fun getHelpText(): String {
        return "Adds a custom text to the claim"
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
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, "custom")
    }
}

