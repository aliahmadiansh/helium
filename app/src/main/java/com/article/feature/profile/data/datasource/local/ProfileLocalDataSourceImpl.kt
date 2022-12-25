package com.article.feature.profile.data.datasource.local

import androidx.lifecycle.LiveData
import com.article.feature.authentication.data.database.dao.AuthenticationDao
import com.article.feature.authentication.data.database.entity.UserInfoEntity
import com.article.feature.profile.data.database.dao.MyArticleDao
import com.article.feature.profile.data.database.entity.MyArticleEntity
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    private val dao: MyArticleDao,
    private val authenticationDAO: AuthenticationDao
) :
    ProfileLocalDataSource {

    override suspend fun insertMytArticles(articles: List<MyArticleEntity>) =
        dao.insertMyArticle(articles)

    override fun getMyArticleById(id: Int): LiveData<MyArticleEntity> =
        dao.getMyArticleByID(id)

    override fun getMyArticles(): LiveData<List<MyArticleEntity>> =
        dao.getMyArticles()

    override suspend fun getUser(): UserInfoEntity = authenticationDAO.getUserInfo()
}