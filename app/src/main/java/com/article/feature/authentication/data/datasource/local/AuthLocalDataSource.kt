package com.article.feature.authentication.data.datasource.local
import com.article.feature.authentication.data.database.entity.UserInfoEntity


interface AuthLocalDataSource {
    suspend fun addUserInfo(user: UserInfoEntity)
    suspend fun getUserInfo(): UserInfoEntity
    fun saveToken(token: String)
    fun getToken(): String?
    fun saveUserID(userID: Int)
    fun getUserID(): Int
}