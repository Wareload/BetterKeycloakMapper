package net.wareload.mapper.oidc.tokenmapper

import net.wareload.mapper.oidc.CustomProtocolMapper
import org.keycloak.models.ClientSessionContext
import org.keycloak.models.KeycloakSession
import org.keycloak.models.ProtocolMapperModel
import org.keycloak.models.UserSessionModel
import org.keycloak.protocol.oidc.mappers.*
import org.keycloak.provider.ProviderConfigProperty
import org.keycloak.representations.AccessTokenResponse

class ElementOfMultivaluedUserAttribute : AbstractOIDCProtocolMapper(), OIDCAccessTokenMapper, OIDCIDTokenMapper,
    UserInfoTokenMapper {

    companion object {
        const val PROVIDER_ID = "bcm-element-of-multivalued-user-attribute"
        const val DISPLAY_CATEGORY = "Token Mapper"
        const val DISPLAY_TYPE = "Element of multi-valued user attribute"
        const val HELP_TEXT = "Adds an element of a multi-valued user attribute as claim."
        private val configProperties = mutableListOf<ProviderConfigProperty>().apply {
            OIDCAttributeMapperHelper.addTokenClaimNameConfig(this)
            OIDCAttributeMapperHelper.addIncludeInTokensConfig(this, CustomProtocolMapper::class.java)
        }
    }

    override fun getId(): String {
        return PROVIDER_ID
    }

    override fun getHelpText(): String {
        return HELP_TEXT
    }

    override fun getConfigProperties(): MutableList<ProviderConfigProperty> {
        return ElementOfMultivaluedUserAttribute.configProperties
    }

    override fun getDisplayCategory(): String {
        return DISPLAY_CATEGORY
    }

    override fun getDisplayType(): String {
        return DISPLAY_TYPE
    }

    override fun setClaim(
        accessTokenResponse: AccessTokenResponse?,
        mappingModel: ProtocolMapperModel?,
        userSession: UserSessionModel?,
        keycloakSession: KeycloakSession?,
        clientSessionCtx: ClientSessionContext?
    ) {
        super.setClaim(accessTokenResponse, mappingModel, userSession, keycloakSession, clientSessionCtx)
    }
}