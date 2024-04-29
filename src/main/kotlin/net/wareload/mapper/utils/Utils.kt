package net.wareload.mapper.utils
import org.keycloak.models.ProtocolMapperModel
import org.keycloak.representations.IDToken

class Utils {

    companion object {
        fun addExistingClaimsInsteadOfOverride(mappingModel: ProtocolMapperModel, token: IDToken): MutableMap<String, Any> {
            val map = emptyMap<String,Any>().toMutableMap()
            val clamName = mappingModel.config.get("claim.name")
            val otherClaims = token.otherClaims[clamName]
            if (otherClaims != null && otherClaims is Map<*, *>) {
                for ((key, value) in otherClaims.entries) {
                    map[key.toString()] = value as Any
                }
            }
            return map
        }
    }

}