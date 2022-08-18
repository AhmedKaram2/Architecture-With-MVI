package com.karam.easymvi.features.authentication.ui.loginActivity.intent

import com.karam.easymvi.features.authentication.data.model.LoginResponse

sealed class LoginIntent {

    class LoginUserEvent(val userName:String , val password:String) :LoginIntent()
    class SaveUserEvent(val user:LoginResponse) : LoginIntent()
}