package com.article.feature.article.data.newtwork.model



data class ArticleResponse(
    val currentPage: Int,
    val `data`: List<DataArticleResponse>,
    val totalPage: Int
)