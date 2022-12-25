package com.article.feature.article.data.repository

import com.article.feature.article.data.datasource.remote.TagHomeRemoteDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.article.core.common.ApiResultWrapper
import com.article.core.ui.model.ItemTagView
import com.article.core.ui.model.toTagView
import com.article.feature.article.data.database.entity.toTagEntity
import com.article.feature.article.data.datasource.local.TagHomeLocalDataSource
import com.article.feature.article.data.newtwork.model.DataTagResponse
import com.article.feature.article.data.newtwork.model.TageResponse


import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagRemoteDataSource: TagHomeRemoteDataSource,
    private val tagLocalDataSource: TagHomeLocalDataSource
) :
    TagRepository {

    val getTagListLocal: LiveData<List<ItemTagView>> = MediatorLiveData<List<ItemTagView>>().apply {
        addSource(tagLocalDataSource.getTags()) { list ->
            postValue(list.map { it.toTagView() })
        }
    }

    override suspend fun getTags(): ApiResultWrapper<TageResponse> =
        tagRemoteDataSource.getTags()

    suspend fun saveTagToLocal(tags: List<DataTagResponse>) =
        tagLocalDataSource.insertTag(tags.map { it.toTagEntity() })

}