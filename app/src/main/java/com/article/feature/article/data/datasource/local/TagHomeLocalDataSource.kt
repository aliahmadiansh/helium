package com.article.feature.article.data.datasource.local
import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.entity.TagEntity


interface TagHomeLocalDataSource {

    suspend fun insertTag(tags: List<TagEntity>)
     fun getTags(): LiveData<List<TagEntity>>
}