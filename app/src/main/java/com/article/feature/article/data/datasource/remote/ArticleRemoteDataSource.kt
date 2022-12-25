package com.article.feature.article.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.newtwork.model.ArticleResponse
import com.article.feature.article.data.newtwork.model.DetailArticleResponse
import com.article.feature.article.data.newtwork.model.PublishArticleBody
import com.article.feature.article.data.newtwork.model.PublishArticleResponse

interface ArticleRemoteDataSource {

    suspend fun getArticles(): ApiResultWrapper<ArticleResponse>
    suspend fun getArticleByID(id: Int): ApiResultWrapper<DetailArticleResponse>
    suspend fun publishArticle(articleData: PublishArticleBody): ApiResultWrapper<PublishArticleResponse>
    suspend fun filterArticleByTag(tags: String): ApiResultWrapper<ArticleResponse>
}