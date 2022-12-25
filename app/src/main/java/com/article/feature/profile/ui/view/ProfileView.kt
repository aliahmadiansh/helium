package com.article.feature.profile.ui.view



data class ProfileView(
    val articles: List<ProfileArticleView>,
    val firstName: String,
    val id: Int,
    val lastName: String,
)
