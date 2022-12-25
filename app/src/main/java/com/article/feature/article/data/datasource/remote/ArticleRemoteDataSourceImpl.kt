package com.article.feature.article.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.newtwork.api.ArticleApi
import com.article.feature.article.data.newtwork.model.ArticleResponse
import com.article.feature.article.data.newtwork.model.DetailArticleResponse
import com.article.feature.article.data.newtwork.model.PublishArticleBody
import com.article.feature.article.data.newtwork.model.PublishArticleResponse
import safeApiCall
import javax.inject.Inject

class ArticleRemoteDataSourceImpl @Inject constructor(private val api: ArticleApi) :
    ArticleRemoteDataSource {

    override suspend fun getArticles(): ApiResultWrapper<ArticleResponse> =
        safeApiCall { api.getArticles() }

    override suspend fun getArticleByID(id: Int): ApiResultWrapper<DetailArticleResponse> =
        safeApiCall { api.getArticleInfoByID(id) }

    override suspend fun publishArticle(articleData: PublishArticleBody):
            ApiResultWrapper<PublishArticleResponse> =
        safeApiCall { api.publishArticle(articleData) }

    override suspend fun filterArticleByTag(tags: String): ApiResultWrapper<ArticleResponse> =
        safeApiCall { api.getArticleByTags(tags) }
}