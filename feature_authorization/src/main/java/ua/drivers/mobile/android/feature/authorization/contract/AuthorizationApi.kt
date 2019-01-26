package ua.drivers.mobile.android.feature.authorization.contract

/**
 *
 */
interface AuthorizationApi {
    fun login(username: String, password: String): Result<User>
    fun logout()
}