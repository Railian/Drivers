package ua.drivers.mobile.android.feature.authorization.presentation.model

/**
 * Authentication result : success (user details) or error message.
 */
sealed class LoginResult {
    object Loading : LoginResult()
    data class Success(val loggedInUser: LoggedInUserViewEntity) : LoginResult()
    data class Error(val errorMessageId: Int) : LoginResult()
}

