package com.article.feature.article.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
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
    val tagName: String
)

fun ArticleEntity.toBookmarkEntity(): BookmarkEntity =
    BookmarkEntity(
        articleId,
        title,
        content,
        readTimeMinutes,
        createdAt,
        authorId,
        authorFirstName,
        authorLastName,
        authorUsername,
        tagId,
        tagName
    )