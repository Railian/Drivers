package ua.drivers.mobile.android.feature.authorization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.drivers.mobile.android.feature.authorization.domain.LoginRepository
import ua.drivers.mobile.android.feature.authorization.contract.Result

import ua.drivers.mobile.android.feature.authorization.R
import ua.drivers.mobile.android.feature.authorization.domain.EmailValidator
import ua.drivers.mobile.android.feature.authorization.domain.PasswordValidator
import ua.drivers.mobile.android.feature.authorization.presentation.model.LoginValidationResult
import ua.drivers.mobile.android.feature.authorization.presentation.model.LoggedInUserViewEntity
import ua.drivers.mobile.android.feature.authorization.presentation.model.LoginResult

class LoginViewModel(
        private val loginRepository: LoginRepository,
        private val emailValidator: EmailValidator,
        private val passwordValidator: PasswordValidator
) : ViewModel() {

    private val _validationResult = MutableLiveData<LoginValidationResult>()
    val validationResult: LiveData<LoginValidationResult> = _validationResult

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult


    fun login(username: String, password: String) {

        _loginResult.value = LoginResult.Loading
        val loginValidationResult =
                LoginValidationResult(
                        isEmailValid = emailValidator.validate(username),
                        isPasswordValid = passwordValidator.validate(password)
                )
        _validationResult.value = loginValidationResult

        if (loginValidationResult.isDataValid) {
            val result = loginRepository.login(username, password)

            if (result is Result.Success) {
                _loginResult.value = LoginResult.Success(
                        loggedInUser = LoggedInUserViewEntity(
                                displayName = result.data.displayName
                        )
                )
            } else {
                _loginResult.value =
                        LoginResult.Error(errorMessageId = R.string.login_failed)
            }
        } else _loginResult.value = null
    }
}
