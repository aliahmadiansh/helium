package com.article.feature.article.data.newtwork.model


import com.google.gson.annotations.SerializedName

data class FromThisUserResponse(
    @SerializedName("author_id")
    val authorId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("read_time_minutes")
    val readTimeMinutes: String,
    val tags: List<DataTagResponse>,
    val title: String
)