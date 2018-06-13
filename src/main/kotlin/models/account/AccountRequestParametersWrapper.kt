package models.account

import models.account.Account
import utils.requestparameters.IParam
import utils.stdlibextensions.string.trimAndSquishWhiteSpace

import java.sql.Timestamp


class AccountRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val email: String? by lazy { requestParameters.get("email")?.string?.trimAndSquishWhiteSpace() }
    val password: String? by lazy { requestParameters.get("password")?.string }
    val passwordConfirmation: String? by lazy { requestParameters.get("passwordConfirmation")?.string }

}