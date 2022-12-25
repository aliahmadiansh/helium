package com.article.feature.profile.ui.view

import com.article.feature.profile.data.database.entity.MyArticleEntity
import com.article.feature.profile.data.network.model.ProfileDataArticleResponse

data class ProfileArticleView(
    val id: Int,
    val tag: String,
    val tagId: Int,
    val title: String,
    val createdAt: String,
    val lastName: String,
    val firstName: String,
    val imageUrl: String
)

fun ProfileDataArticleResponse.toProfileArticleView(lastName: String, firstName: String) = ProfileArticleView(
    id = id,
    tag = tags[0].name,
    tagId = tags[0].id,
    title = title,
    createdAt = createdAt,
    lastName = lastName,
    firstName = firstName,
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU"
)

fun MyArticleEntity.toProfileArticleView() = ProfileArticleView(
    id = articleId,
    tag = tagName,
    tagId = tagId,
    title = title,
    createdAt = createdAt,
    lastName = authorLastName,
    firstName = authorFirstName,
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm34rv5onXNGrPvQIJ3xhcUL5ldjamyQikNQ&usqp=CAU"
)