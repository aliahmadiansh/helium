package com.article.feature.search.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.search.data.network.model.SearchBody
import com.article.feature.search.data.network.model.SearchResponse


interface SearchRemoteDataSource {
    suspend fun postSearch(data: SearchBody): ApiResultWrapper<SearchResponse>
}