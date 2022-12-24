package com.article.feature.authentication.data.network.api


import com.article.feature.authentication.data.network.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("auth/signup")
    suspend fun postUserSignUp(@Body data: UserSignUpBody): Response<Unit>

    @POST("auth/login")
    suspend fun postUserSignIn(@Body data: UserSignInBody): Response<UserSignInResponse>

    @POST("auth/user-exist")
    suspend fun postUserExist(@Body data: UserExistBody): Response<UserExistResponse>

    @GET("user")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}