package com.article.core.ui.model

import com.article.feature.article.data.database.entity.TagEntity


data class ItemTagView(
    val id: Int,
    val name: String
)

fun TagEntity.toTagView() = ItemTagView(
    id = tagId,
    name = name
)
