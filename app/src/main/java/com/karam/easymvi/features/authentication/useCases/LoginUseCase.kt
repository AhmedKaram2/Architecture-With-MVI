package com.karam.easymvi.features.authentication.useCases

import com.karam.easymvi.core.apiSettings.BaseUseCase
import com.karam.easymvi.features.authentication.data.apis.AuthenticationDataSource
import com.karam.easymvi.features.authentication.data.model.LoginResponse
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) : BaseUseCase() {

    @Throws(Throwable::class)
    suspend fun userLogin(
        userName: String, password: String
    ): LoginResponse {
        // Validate
        if (userName.isEmpty()) {
            throw Exception("Username is empty")
        }

        if (password.isEmpty()) {
            throw Exception("Password is empty")
        }

        return authenticationDataSource.userLogin(userName, password
        )
    }
}