package com.karam.easymvi.features.authentication.data.apis

import com.karam.easymvi.features.authentication.data.model.LoginResponse
import javax.inject.Inject

class AuthenticationDataSource
@Inject constructor(private val api: AuthenticationAPI) {

    suspend fun userLogin(username: String, password: String): LoginResponse {
        return api.userLogin(username, password)
    }
}