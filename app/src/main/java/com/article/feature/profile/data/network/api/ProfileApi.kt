package com.article.feature.profile.data.network.api


import com.article.feature.profile.data.network.model.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("profile")
    suspend fun profile(@Query("id") id: Int):Response<ProfileResponse>
}