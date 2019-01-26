package ua.drivers.mobile.android.feature.authorization.entity

import ua.drivers.mobile.android.feature.authorization.contract.User

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class UserEntity(
    override val userId: String,
    override val displayName: String
): User