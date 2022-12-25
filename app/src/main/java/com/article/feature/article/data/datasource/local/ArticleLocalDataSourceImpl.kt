package com.article.feature.article.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.dao.ArticleDao
import com.article.feature.article.data.database.dao.BookmarkDao
import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity
import javax.inject.Inject

class ArticleLocalDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val bookmarkDao: BookmarkDao
) : ArticleLocalDataSource {

    override suspend fun insertArticles(articles: List<ArticleEntity>) =
        articleDao.insertArticle(articles)

    override suspend fun updateArticle(article: ArticleEntity) {
        articleDao.updateArticle(article)
    }

    override fun getArticles(): LiveData<List<ArticleEntity>> = articleDao.getArticles()

    override fun getArticleByID(id: Int): LiveData<ArticleEntity> =
        articleDao.getArticleByID(id)

    override fun getArticlesByTag(ids: List<Int>): LiveData<List<ArticleEntity>> =
        articleDao.getArticlesByTag(ids)

    override suspend fun insertBookmark(article: BookmarkEntity) {
        bookmarkDao.insertBookmark(article)
    }

    override suspend fun deleteBookmarked(article: BookmarkEntity) {
        bookmarkDao.deleteBookmarked(article)
    }

    override suspend fun getBookmarked(articleID: Int): Boolean =
        bookmarkDao.getBookmarked(articleID)
}