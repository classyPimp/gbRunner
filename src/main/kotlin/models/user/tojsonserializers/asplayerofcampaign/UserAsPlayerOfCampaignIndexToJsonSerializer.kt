package models.user.tojsonserializers.asplayerofcampaign

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer

object UserAsPlayerOfCampaignIndexToJsonSerializer {

    fun onSuccess(users: MutableList<User>): String {
        UserToJsonSerializer.serialize(users).let {
            return it.serializeToString()
        }
    }

    fun onError(user: User): String {
        UserToJsonSerializer(user). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}