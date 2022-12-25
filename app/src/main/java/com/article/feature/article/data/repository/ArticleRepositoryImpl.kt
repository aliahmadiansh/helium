package com.article.feature.article.data.repository

import androidx.lifecycle.LiveData
import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity
import com.article.feature.article.data.database.entity.toArticleEntity
import com.article.feature.article.data.datasource.local.ArticleLocalDataSource
import com.article.feature.article.data.datasource.remote.ArticleRemoteDataSource
import com.article.feature.article.data.newtwork.model.*
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    private val articleLocalDataSource: ArticleLocalDataSource
) : ArticleRepository {

    override suspend fun fetchArticles(): ApiResultWrapper<ArticleResponse> =
        articleRemoteDataSource.getArticles()

    override suspend fun publishArticle(articleBody: PublishArticleBody): ApiResultWrapper<PublishArticleResponse> =
        articleRemoteDataSource.publishArticle(articleBody)

    override suspend fun insertArticles(articles: List<ArticleEntity>) {
        articleLocalDataSource.insertArticles(articles)
    }

    override fun getArticleByID(id: Int): LiveData<ArticleEntity> =
        articleLocalDataSource.getArticleByID(id)

    override fun getArticlesByTag(ids: List<Int>): LiveData<List<ArticleEntity>> =
        articleLocalDataSource.getArticlesByTag(ids)

    override suspend fun fetchArticleByTag(tags: String): ApiResultWrapper<ArticleResponse> =
        articleRemoteDataSource.filterArticleByTag(tags)

    suspend fun saveArticleToLocal(articles: List<DataArticleResponse>) =
            articleLocalDataSource.insertArticles(articles.map { it.toArticleEntity() })

    override suspend fun updateArticle(article: ArticleEntity) {
        articleLocalDataSource.updateArticle(article)
    }

    override fun getArticles(): LiveData<List<ArticleEntity>> =
        articleLocalDataSource.getArticles()

    override suspend fun fetchArticleByID(id: Int): ApiResultWrapper<DetailArticleResponse> =
        articleRemoteDataSource.getArticleByID(id)

    override suspend fun insertBookmark(article: BookmarkEntity) {
        articleLocalDataSource.insertBookmark(article)
    }

    override suspend fun deleteBookmarked(article: BookmarkEntity) {
        articleLocalDataSource.deleteBookmarked(article)
    }

    override suspend fun getBookmarked(articleID: Int): Boolean =
        articleLocalDataSource.getBookmarked(articleID)
}