package com.article.feature.article.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.newtwork.model.TageResponse


interface TagHomeRemoteDataSource {

    suspend fun getTags(): ApiResultWrapper<TageResponse>
}