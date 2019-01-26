package ua.drivers.mobile.android.feature.authorization.data

import ua.drivers.mobile.android.feature.authorization.contract.AuthorizationApi
import ua.drivers.mobile.android.feature.authorization.contract.Result
import ua.drivers.mobile.android.feature.authorization.contract.User
import ua.drivers.mobile.android.feature.authorization.entity.UserEntity
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class AuthorizationApiImpl :AuthorizationApi {

    override fun login(username: String, password: String): Result<User> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = UserEntity(
                UUID.randomUUID().toString(),
                "Jane Doe"
            )
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    override fun logout() {
        // TODO: revoke authentication
    }
}

