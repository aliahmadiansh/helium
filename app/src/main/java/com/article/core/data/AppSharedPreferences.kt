package com.article.core.data

interface AppSharedPreferences {

    fun saveToken(token: String)
    fun getToken(): String?
    fun saveUserID(userID: Int)
    fun getUserID(): Int
}