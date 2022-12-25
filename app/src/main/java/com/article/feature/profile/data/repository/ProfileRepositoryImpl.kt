package com.article.feature.profile.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.article.core.common.ApiResultWrapper
import com.article.core.data.AppSharedPreferences
import com.article.feature.profile.data.database.entity.toArticleEntity
import com.article.feature.profile.data.datasource.local.ProfileLocalDataSource
import com.article.feature.profile.data.datasource.remote.ProfileRemoteDataSource
import com.article.feature.profile.data.network.model.ProfileResponse
import com.article.feature.profile.ui.view.ProfileArticleView
import com.article.feature.profile.ui.view.toProfileArticleView

import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val appSharedPreferences: AppSharedPreferences

) : ProfileRepository {

    val getArticleList: LiveData<List<ProfileArticleView>> =
        MediatorLiveData<List<ProfileArticleView>>().apply {
            addSource(profileLocalDataSource.getMyArticles()) { list ->
                postValue(list.map { it.toProfileArticleView() })
            }
        }

    override suspend fun profile(id: Int): ApiResultWrapper<ProfileResponse> =
        profileRemoteDataSource.profile(id)

    override fun getUserId(): Int = appSharedPreferences.getUserID()
    override suspend fun getFullName(): String =
        profileLocalDataSource.getUser().firstName + " " + profileLocalDataSource.getUser().lastName

    suspend fun saveMyArticleToLocal(articles: ProfileResponse) =
        profileLocalDataSource.insertMytArticles(articles.articles.data.map {
            it.toArticleEntity(
                userName = articles.username,
                lastName = articles.lastName,
                firstName = articles.firstName
            )
        })

}