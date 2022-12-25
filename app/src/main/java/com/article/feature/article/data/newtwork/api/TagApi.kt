package com.article.feature.article.data.newtwork.api
import com.article.feature.article.data.newtwork.model.TageResponse
import retrofit2.Response
import retrofit2.http.GET

interface TagApi {

    @GET("tags")
    suspend fun tags():Response<TageResponse>
}