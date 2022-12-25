package com.article.feature.article.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.article.feature.article.data.newtwork.model.ArticleDataResponse
import com.article.feature.article.data.newtwork.model.DataArticleResponse

@Entity(tableName = "article")
data class ArticleEntity(
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

fun DataArticleResponse.toArticleEntity() = ArticleEntity(
    articleId = id,
    title = title,
    content = content,
    readTimeMinutes = readTimeMinutes,
    createdAt = createdAt,
    authorId = authorId,
    authorFirstName = authorFirstName,
    authorLastName = authorLastName,
    authorUsername = authorUsername,
    tagId = tags[0].id,
    tagName = tags[0].name
)

fun ArticleDataResponse.toArticleEntity(): ArticleEntity =
    ArticleEntity(
        this.id,
        this.title,
        this.content,
        this.readTimeMinutes,
        this.createdAt,
        this.authorId,
        this.authorFirstName,
        this.authorLastName,
        this.authorUsername,
        this.tags[0].id,
        this.tags[0].name
    )
