package com.article.feature.search.data.repository

import com.article.core.common.ApiResultWrapper
import com.article.feature.search.data.datasource.remote.SearchRemoteDataSource
import com.article.feature.search.data.network.model.SearchBody
import com.article.feature.search.data.network.model.SearchResponse

import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
): SearchRepository {

    override suspend fun postSearch(text: String): ApiResultWrapper<SearchResponse> {
        return remoteDataSource.postSearch(SearchBody(text))
    }
}