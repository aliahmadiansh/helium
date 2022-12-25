package com.article.feature.article.data.newtwork.model

import com.google.gson.annotations.SerializedName

data class PublishArticleResponse(

    @SerializedName("article_id") val articleId: Int,
    val message: String,
)