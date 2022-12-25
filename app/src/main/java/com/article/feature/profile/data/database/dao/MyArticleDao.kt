package com.article.feature.profile.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.article.feature.profile.data.database.entity.MyArticleEntity


@Dao
interface MyArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyArticle(articles: List<MyArticleEntity>)

    @Query("SELECT * FROM my_article")
    fun getMyArticles(): LiveData<List<MyArticleEntity>>

    @Query("SELECT * FROM my_article WHERE article_id LIKE :id")
    fun getMyArticleByID(id: Int): LiveData<MyArticleEntity>
}