package com.article.feature.profile.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.authentication.data.database.entity.UserInfoEntity
import com.article.feature.profile.data.database.entity.MyArticleEntity

interface ProfileLocalDataSource {

    suspend fun insertMytArticles(articles: List<MyArticleEntity>)
    fun getMyArticleById(id: Int): LiveData<MyArticleEntity>
    fun getMyArticles(): LiveData<List<MyArticleEntity>>
    suspend fun getUser(): UserInfoEntity

}