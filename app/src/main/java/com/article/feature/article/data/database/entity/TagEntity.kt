package com.article.feature.article.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.article.feature.article.data.newtwork.model.DataTagResponse


@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey
    @ColumnInfo(name = "tag_id")
    val tagId: Int,
    val name: String
)

fun DataTagResponse.toTagEntity() = TagEntity(
    tagId = id,
    name = name
)