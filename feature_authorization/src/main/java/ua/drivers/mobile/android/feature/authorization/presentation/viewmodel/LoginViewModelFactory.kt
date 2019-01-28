package ua.drivers.mobile.android.feature.authorization.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.drivers.mobile.android.feature.authorization.data.AuthorizationApiImpl
import ua.drivers.mobile.android.feature.authorization.domain.EmailValidator
import ua.drivers.mobile.android.feature.authorization.domain.LoginRepository
import ua.drivers.mobile.android.feature.authorization.domain.PasswordValidator

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = AuthorizationApiImpl()
                ),
                emailValidator = EmailValidator(),
                passwordValidator = PasswordValidator()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
