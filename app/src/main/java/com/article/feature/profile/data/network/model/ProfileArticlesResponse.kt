package com.article.feature.profile.data.network.model

data class ProfileArticlesResponse(
    val currentPage: Int,
    val `data`: List<ProfileDataArticleResponse>,
    val totalPage: Int
)