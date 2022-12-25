package com.article.feature.article.data.repository


import androidx.lifecycle.LiveData
import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity
import com.article.feature.article.data.newtwork.model.ArticleResponse
import com.article.feature.article.data.newtwork.model.DetailArticleResponse
import com.article.feature.article.data.newtwork.model.PublishArticleBody
import com.article.feature.article.data.newtwork.model.PublishArticleResponse

interface ArticleRepository {

    suspend fun fetchArticles(): ApiResultWrapper<ArticleResponse>
    suspend fun fetchArticleByID(id: Int): ApiResultWrapper<DetailArticleResponse>
    suspend fun fetchArticleByTag(tags: String): ApiResultWrapper<ArticleResponse>
    suspend fun publishArticle(articleBody: PublishArticleBody): ApiResultWrapper<PublishArticleResponse>
    suspend fun insertArticles(articles: List<ArticleEntity>)
    suspend fun updateArticle(article: ArticleEntity)
    fun getArticles(): LiveData<List<ArticleEntity>>
    fun getArticleByID(id: Int): LiveData<ArticleEntity>
    fun getArticlesByTag(ids: List<Int>): LiveData<List<ArticleEntity>>

    suspend fun insertBookmark(article: BookmarkEntity)
    suspend fun deleteBookmarked(article: BookmarkEntity)
    suspend fun getBookmarked(articleID: Int): Boolean
}