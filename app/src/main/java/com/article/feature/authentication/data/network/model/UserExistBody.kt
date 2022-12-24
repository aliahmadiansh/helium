package com.article.feature.authentication.data.network.model

import com.google.gson.annotations.SerializedName

data class UserExistBody(
    @SerializedName("phone_number")
    val phoneNumber: String
)
