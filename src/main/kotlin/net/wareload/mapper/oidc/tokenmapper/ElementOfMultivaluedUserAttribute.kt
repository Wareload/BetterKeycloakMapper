package net.wareload.mapper.oidc.tokenmapper

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
        private const val PROVIDER_ID = "bcm-element-of-multivalued-user-attribute"
        private const val DISPLAY_CATEGORY = "Token Mapper"
        private const val DISPLAY_TYPE = "BCM Element of multi-valued user attribute"
        private const val HELP_TEXT = "Adds elements of a multi-valued user attribute as claim."
        private const val FIELD_ELEMENTS = "elements"
        private const val FIELD_EMITTED = "emitted"
        private const val DEFAULT_CLAIM_NAME = "bcm-element-of-multivalued-user-attribute"
        private val configProperties = ArrayList<ProviderConfigProperty>()

        init {
            configProperties.add(
                ProviderConfigProperty(
                    FIELD_ELEMENTS,
                    "Elements",
                    "Choose elements of the user attributes to include.",
                    ProviderConfigProperty.MULTIVALUED_STRING_TYPE,
                    null
                )
            )
            configProperties.add(
                ProviderConfigProperty(
                    FIELD_EMITTED,
                    "Emitted as array",
                    "Check if the claim value should be emitted as array or string. If there are multiple elements to be emitted and string is selected, only the first element will be emitted.",
                    ProviderConfigProperty.BOOLEAN_TYPE,
                    true
                )
            )
            OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
            OIDCAttributeMapperHelper.addJsonTypeConfig(configProperties);
            OIDCAttributeMapperHelper.addIncludeInTokensConfig(
                configProperties,
                ElementOfMultivaluedUserAttribute::class.java
            )
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