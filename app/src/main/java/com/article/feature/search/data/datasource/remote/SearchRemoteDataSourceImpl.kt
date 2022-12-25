package com.article.feature.search.data.datasource.remote
import com.article.core.common.ApiResultWrapper
import com.article.feature.search.data.network.api.SearchApi
import com.article.feature.search.data.network.model.SearchBody
import com.article.feature.search.data.network.model.SearchResponse
import safeApiCall
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val searchApi: SearchApi): SearchRemoteDataSource {
    override suspend fun postSearch(data: SearchBody): ApiResultWrapper<SearchResponse> =
        safeApiCall { searchApi.postSearch(data) }
}