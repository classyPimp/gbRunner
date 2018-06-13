package models.user

import models.account.AccountRequestParametersWrapper
import models.user.User
import utils.requestparameters.IParam
import utils.stdlibextensions.string.trimAndSquishWhiteSpace

class UserRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long() }
    val name: String? by lazy { requestParameters.get("name")?.string?.trimAndSquishWhiteSpace() }
    val account: AccountRequestParametersWrapper? by lazy {
        requestParameters.get("account")?.let {
            AccountRequestParametersWrapper(it)
        }
    }

}