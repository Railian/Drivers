package ua.drivers.mobile.android.feature.authorization.presentation.view

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import ua.drivers.mobile.android.feature.authorization.R
import ua.drivers.mobile.android.feature.authorization.presentation.viewmodel.LoginViewModel
import ua.drivers.mobile.android.feature.authorization.presentation.model.LoggedInUserViewEntity
import ua.drivers.mobile.android.feature.authorization.presentation.model.LoginResult
import ua.drivers.mobile.android.feature.authorization.presentation.viewmodel.LoginViewModelFactory

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_authorization)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory()
        )
            .get(LoginViewModel::class.java)

        loginViewModel.validationResult.observe(this@AuthorizationActivity, Observer {validationResult->
            validationResult ?: return@Observer

            username.error = if (validationResult.isEmailValid) null else "Email is not valid"
            password.error = if (validationResult.isPasswordValid) null else "Password is not valid"

        })

        loginViewModel.loginResult.observe(this@AuthorizationActivity, Observer {loginResult ->

            if(loginResult is LoginResult.Loading)loading.visibility = View.VISIBLE
            else{
                loading.visibility = View.GONE

                if (loginResult is LoginResult.Error ) {
                    showLoginFailed(loginResult.errorMessageId)
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
                if (loginResult is LoginResult.Success) {
                    updateUiWithUser(loginResult.loggedInUser)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        })

        username.afterTextChanged {
            username.error = null
        }

        password.apply {
            afterTextChanged {
                password.error = null
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginViewModel.login(
                    username.text.toString(),
                    password.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserViewEntity) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}