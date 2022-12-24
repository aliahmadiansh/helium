package com.article.core.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPreferencesImpl @Inject constructor(@ApplicationContext context: Context) :
    AppSharedPreferences {

    private val sharedPreferences =
        context.getSharedPreferences("Helium", Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString("Token", token).apply()
    }

    override fun getToken(): String? = sharedPreferences.getString("Token", null)

    override fun saveUserID(userID: Int) {
        sharedPreferences.edit().putInt("UserID", userID).apply()
    }

    override fun getUserID(): Int = sharedPreferences.getInt("UserID", 0)
}