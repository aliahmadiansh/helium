package com.article.feature.article.data.datasource.remote
import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.newtwork.api.TagApi
import com.article.feature.article.data.newtwork.model.TageResponse
import safeApiCall
import javax.inject.Inject

class TagHomeRemoteDataSourceImpl @Inject constructor(private val api: TagApi) :
    TagHomeRemoteDataSource {
    override suspend fun getTags(): ApiResultWrapper<TageResponse> {
        return safeApiCall { api.tags() }
    }
}