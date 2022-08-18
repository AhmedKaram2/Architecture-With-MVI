package com.karam.easymvi.features.authentication.data.apis

import com.karam.easymvi.core.apiSettings.Const
import com.karam.easymvi.features.authentication.data.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationAPI {

    @FormUrlEncoded
    @POST("token")
    @Headers(Const.NO_AUTH_HEADER_KEY)
    suspend fun userLogin(
        @Field("UserName") userName: String,
        @Field("password") password: String,
    ): LoginResponse
}