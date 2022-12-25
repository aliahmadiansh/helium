package com.article.feature.article.data.database.dao

import com.article.feature.article.data.database.entity.ArticleEntity
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articles: List<ArticleEntity>)

    @Update
    fun updateArticle(article: ArticleEntity)

    @Query("SELECT * FROM article ORDER BY article_id DESC")
    fun getArticles(): LiveData<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE article_id LIKE :id")
    fun getArticleByID(id: Int): LiveData<ArticleEntity>

    @Query("SELECT * FROM article WHERE tag_id IN (:ids)")
    fun getArticlesByTag(ids: List<Int>): LiveData<List<ArticleEntity>>
}