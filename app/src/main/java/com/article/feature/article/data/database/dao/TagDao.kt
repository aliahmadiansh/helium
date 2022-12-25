package com.article.feature.article.data.database.dao
import com.article.feature.article.data.database.entity.TagEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tags: List<TagEntity>)

    @Query("SELECT * FROM tag")
    fun getTags(): LiveData<List<TagEntity>>


}