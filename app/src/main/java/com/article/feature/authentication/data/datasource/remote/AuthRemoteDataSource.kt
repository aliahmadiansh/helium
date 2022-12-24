package com.article.feature.authentication.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.network.model.*

interface AuthRemoteDataSource {
    suspend fun postUserSignUp(data: UserSignUpBody): ApiResultWrapper<Unit>
    suspend fun postUserExist(data: UserExistBody): ApiResultWrapper<UserExistResponse>
    suspend fun postUserSignIn(data: UserSignInBody): ApiResultWrapper<UserSignInResponse>
    suspend fun getUserInfo(): ApiResultWrapper<UserInfoResponse>
}