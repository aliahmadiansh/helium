package com.article.feature.search.data.network.api



import com.article.feature.search.data.network.model.SearchBody
import com.article.feature.search.data.network.model.SearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchApi {

    // Success -> com.article.feature.search.data.network.model.SearchResponse
    // Failure -> Token Error and ...
    @POST("search")
    suspend fun postSearch(@Body data: SearchBody): Response<SearchResponse>
}