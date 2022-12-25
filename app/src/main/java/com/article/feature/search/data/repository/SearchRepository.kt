package com.article.feature.search.data.repository

import com.article.core.common.ApiResultWrapper
import com.article.feature.search.data.network.model.SearchResponse


interface SearchRepository {
    suspend fun postSearch(text: String): ApiResultWrapper<SearchResponse>
}