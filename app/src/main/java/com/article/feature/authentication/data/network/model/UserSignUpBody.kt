package com.article.feature.authentication.data.network.model

import com.google.gson.annotations.SerializedName

data class UserSignUpBody(
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val password: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)
