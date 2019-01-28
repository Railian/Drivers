package ua.drivers.mobile.android.feature.authorization.domain

import ua.drivers.mobile.android.feature.authorization.contract.Validator

class EmailValidator:Validator<String,Boolean> {
    override fun validate(value: String): Boolean {
        when{
            (!value.contains("@")) -> return false
            (!value.contains(".")) -> return false
            (value.split(".").last().length < 2) -> return false
            else -> return true
        }
    }
}