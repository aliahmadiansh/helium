package com.article.feature.profile.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.article.feature.profile.data.network.model.ProfileDataArticleResponse


@Entity(tableName = "my_article")
data class MyArticleEntity(
    @PrimaryKey
    @ColumnInfo(name = "article_id")
    val articleId: Int,
    val title: String,
    val content: String,
    @ColumnInfo(name = "read_time_minutes")
    val readTimeMinutes: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "author_id")
    val authorId: Int,
    @ColumnInfo(name = "author_first_name")
    val authorFirstName: String,
    @ColumnInfo(name = "author_last_name")
    val authorLastName: String,
    @ColumnInfo(name = "author_username")
    val authorUsername: String,
    @ColumnInfo(name = "tag_id")
    val tagId: Int,
    @ColumnInfo(name = "tag_name")
    val tagName: String,
)

fun ProfileDataArticleResponse.toArticleEntity(
    firstName: String,
    lastName: String,
    userName: String
) = MyArticleEntity(
    articleId = id,
    title = title,
    content = content,
    readTimeMinutes = readTimeMinutes,
    createdAt = createdAt,
    authorId = authorId,
    authorFirstName = firstName,
    authorUsername = userName,
    authorLastName =lastName ,
    tagId = tags[0].id,
    tagName = tags[0].name
)

