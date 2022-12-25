package com.article.feature.article.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity

interface ArticleLocalDataSource {

    suspend fun insertArticles(articles: List<ArticleEntity>)
    suspend fun updateArticle(article: ArticleEntity)
    fun getArticles(): LiveData<List<ArticleEntity>>
    fun getArticleByID(id: Int): LiveData<ArticleEntity>
    fun getArticlesByTag(ids: List<Int>): LiveData<List<ArticleEntity>>

    suspend fun insertBookmark(article: BookmarkEntity)
    suspend fun deleteBookmarked(article: BookmarkEntity)
    suspend fun getBookmarked(articleID: Int): Boolean
}