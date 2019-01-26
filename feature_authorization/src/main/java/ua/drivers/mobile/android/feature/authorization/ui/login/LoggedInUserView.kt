package ua.drivers.mobile.android.feature.authorization.ui.login

/**
 * UserEntity details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String
    //... other data fields that may be accessible to the UI
)
