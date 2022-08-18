package com.karam.easymvi.features.authentication.ui.loginActivity.state

import com.karam.easymvi.features.authentication.data.model.LoginResponse

sealed class LoginViewState {

    object Idle : LoginViewState()
    object Loading : LoginViewState()
    data class User(val user: LoginResponse?) : LoginViewState()
    data class Error(val error: String? = null, val errorMsgId: Int? = null) : LoginViewState()
}