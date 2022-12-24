package com.article.feature.authentication.data.network.model

import com.google.gson.annotations.SerializedName

data class UserSignInBody(
    @SerializedName("phone_number")
    val phoneNumber: String,
    val password: String
)
