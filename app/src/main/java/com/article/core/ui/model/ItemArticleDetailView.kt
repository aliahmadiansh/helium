package com.article.core.ui.model

import com.article.feature.article.data.database.entity.ArticleEntity


data class ItemArticleDetailView(
    val id: Int,
    val title: String,
    val content: String,
    val authorId: Int,
    val authorFirstName: String,
    val authorLastName: String,
    val readTimeMinutes: String,
    val tagId: Int,
    val tagName: String,
    val createdAt: String,
    val imageURL: String,
)

fun ArticleEntity.toArticleDetailView(): ItemArticleDetailView =
    ItemArticleDetailView(
        articleId,
        title,
        content,
        authorId,
        authorFirstName,
        authorLastName,
        readTimeMinutes,
        tagId,
        tagName,
        createdAt,
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU",
    )