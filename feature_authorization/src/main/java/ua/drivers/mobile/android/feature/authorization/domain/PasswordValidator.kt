package ua.drivers.mobile.android.feature.authorization.domain

import ua.drivers.mobile.android.feature.authorization.contract.Validator

class PasswordValidator:Validator<String,Boolean> {

    override fun validate(value: String): Boolean {

            value.find { it !in 'a'..'z' }?: return false
            value.find { it !in 'A'..'Z' }?: return false
            value.find { it !in '0'..'9' }?: return false
            return true
    }
}