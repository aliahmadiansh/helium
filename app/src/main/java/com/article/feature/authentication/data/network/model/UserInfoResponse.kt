package com.article.feature.authentication.data.network.model
import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val username: String,
    @SerializedName("created_at")
    val createdAt: String,
)