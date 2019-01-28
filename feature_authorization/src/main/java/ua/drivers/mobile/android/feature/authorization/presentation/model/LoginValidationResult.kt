package ua.drivers.mobile.android.feature.authorization.presentation.model

/**
 * Data validation state of the login form.
 */
data class LoginValidationResult(
    val isEmailValid: Boolean,
    val isPasswordValid: Boolean
) {
    val isDataValid = isEmailValid && isPasswordValid
}


