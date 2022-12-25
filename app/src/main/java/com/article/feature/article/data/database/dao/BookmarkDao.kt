package com.article.feature.article.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.article.feature.article.data.database.entity.BookmarkEntity


@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(article: BookmarkEntity)

    @Delete
    suspend fun deleteBookmarked(article: BookmarkEntity)

    @Query("SELECT EXISTS(SELECT * FROM bookmark WHERE article_id LIKE :articleID)")
    suspend fun getBookmarked(articleID: Int): Boolean

    @Query("SELECT * FROM bookmark ORDER BY article_id ASC")
    fun getBookmarks(): LiveData<List<BookmarkEntity>>
}