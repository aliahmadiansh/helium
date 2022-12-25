package com.article.core.ui.model

import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity

data class ItemArticleView(
    val id: Int,
    val tag: String,
    val tagId: Int,
    val title: String,
    val authorFirstName: String,
    val authorLastName: String,
    val createdAt: String,
    val imageUrl: String,
)

fun ArticleEntity.toArticleView() = ItemArticleView(
    id = articleId,
    title = title,
    authorLastName = authorLastName,
    authorFirstName = authorFirstName,
    createdAt = createdAt,
    tagId = tagId,
    tag = tagName,
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU",
)

fun BookmarkEntity.toArticleView() = ItemArticleView(
    id = articleId,
    title = title,
    authorLastName = authorLastName,
    authorFirstName = authorFirstName,
    createdAt = createdAt,
    tagId = tagId,
    tag = tagName,
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU",
)