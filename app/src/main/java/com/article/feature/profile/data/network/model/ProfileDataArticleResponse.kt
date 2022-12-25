package com.article.feature.profile.data.network.model

import com.google.gson.annotations.SerializedName

data class ProfileDataArticleResponse(
    @SerializedName("author_id")
    val authorId: Int,
    @SerializedName("comments_count")
    val commentsCount: String,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("read_time_minutes")
    val readTimeMinutes: String,
    val tags: List<ProfileTagResponse>,
    val title: String
)