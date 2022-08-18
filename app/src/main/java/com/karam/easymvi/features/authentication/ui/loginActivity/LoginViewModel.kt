package com.karam.easymvi.features.authentication.ui.loginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohre.smartinspectionapp.R
import com.karam.easymvi.features.authentication.ui.loginActivity.intent.LoginIntent
import com.karam.easymvi.features.authentication.ui.loginActivity.state.LoginViewState
import com.karam.easymvi.features.authentication.useCases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<LoginViewState>(LoginViewState.Idle)
    val state: StateFlow<LoginViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {

            loginIntent.consumeAsFlow().collect { loginIntent ->

                when (loginIntent) {

                    is LoginIntent.LoginUserEvent ->
                        userLogin(loginIntent.userName, loginIntent.password)


                    is LoginIntent.SaveUserEvent -> saveUserData()
                }
            }
        }
    }

    private fun userLogin(
        username: String,
        password: String
    ) {
        _state.value = LoginViewState.Loading

        viewModelScope.launch {
            _state.value = try {
                LoginViewState.User(
                    loginUseCase.userLogin(
                        username,
                        password)
                )
            } catch (e: Exception) {
                LoginViewState.Error(
                    error =
                    (e as? HttpException)?.response()?.errorBody()?.string()
                        ?: e.message!!
                )
            }

        }
    }

    private fun saveUserData() {
        // TODO("Not yet implemented")
    }

}