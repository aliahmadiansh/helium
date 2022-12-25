package com.article.feature.profile.data.network.model


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val articles: ProfileArticlesResponse,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    val username: String
)