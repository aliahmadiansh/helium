package com.article.feature.authentication.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.network.api.AuthenticationApi
import com.article.feature.authentication.data.network.model.*
import safeApiCall

import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(private val authenticationAPI: AuthenticationApi):
AuthRemoteDataSource {

    override suspend fun postUserSignUp(data: UserSignUpBody): ApiResultWrapper<Unit> =
        safeApiCall { authenticationAPI.postUserSignUp(data) }

    override suspend fun postUserSignIn(data: UserSignInBody): ApiResultWrapper<UserSignInResponse> =
        safeApiCall { authenticationAPI.postUserSignIn(data) }

    override suspend fun postUserExist(data: UserExistBody): ApiResultWrapper<UserExistResponse> =
        safeApiCall { authenticationAPI.postUserExist(data) }

    override suspend fun getUserInfo(): ApiResultWrapper<UserInfoResponse> =
        safeApiCall { authenticationAPI.getUserInfo() }
}