package com.article.feature.authentication.data.repository
import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.database.entity.UserInfoEntity
import com.article.feature.authentication.data.network.model.*


interface AuthenticationRepository {
    suspend fun postUserSignIn(userInfo: UserSignInBody): ApiResultWrapper<UserSignInResponse>
    suspend fun postUserExist(userInfo: UserExistBody): ApiResultWrapper<UserExistResponse>
    suspend fun postUserSignUp(userInfo: UserSignUpBody): ApiResultWrapper<Unit>
    suspend fun getUserInfo(): ApiResultWrapper<UserInfoResponse>
    suspend fun addUserInfoToDatabase(userInfo: UserInfoEntity)
    suspend fun getUserInfoFromDatabase(): UserInfoEntity
    fun checkExistToken(): Boolean
    fun saveToken(token: String)
    fun getToken(): String?
    fun saveUserID(userID: Int)
    fun getUserID(): Int
}