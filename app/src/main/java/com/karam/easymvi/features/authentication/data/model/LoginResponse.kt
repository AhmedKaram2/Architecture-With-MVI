package com.karam.easymvi.features.authentication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("access_token")
    @Expose
    val accessToken: String,
    @SerializedName( "token_type")
    @Expose
    val tokenType: String?,
    @SerializedName( "user")
    @Expose
    val userName: String?
)