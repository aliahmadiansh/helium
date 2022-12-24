package com.article.feature.authentication.data.datasource.local

import com.article.core.data.AppSharedPreferences
import com.article.feature.authentication.data.database.dao.AuthenticationDao
import com.article.feature.authentication.data.database.entity.UserInfoEntity

import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authenticationDAO: AuthenticationDao,
    private val appSharedPreferences: AppSharedPreferences
) : AuthLocalDataSource {

    override suspend fun addUserInfo(user: UserInfoEntity) {
        authenticationDAO.addUserInfo(user)
    }

    override suspend fun getUserInfo(): UserInfoEntity =
        authenticationDAO.getUserInfo()

    override fun saveToken(token: String) {
        appSharedPreferences.saveToken(token)
    }

    override fun getToken(): String? = appSharedPreferences.getToken()

    override fun saveUserID(userID: Int) {
        appSharedPreferences.saveUserID(userID)
    }

    override fun getUserID(): Int = appSharedPreferences.getUserID()
}