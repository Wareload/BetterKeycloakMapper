package net.wareload.mapper.utils
import org.keycloak.models.ProtocolMapperModel
import org.keycloak.representations.IDToken

class Utils {

    companion object {
        fun getAlreadyExistingClaims(mappingModel: ProtocolMapperModel, token: IDToken): Any? {
            val clamName = mappingModel.config["claim.name"]
            val otherClaims = token.otherClaims[clamName]
            return otherClaims
        }
    }

}