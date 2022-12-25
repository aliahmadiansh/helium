package com.article.feature.profile.data.repository

import com.article.core.common.ApiResultWrapper
import com.article.feature.profile.data.network.model.ProfileResponse


interface ProfileRepository {

    suspend fun profile(id: Int): ApiResultWrapper<ProfileResponse>
    fun getUserId(): Int
    suspend fun getFullName(): String

}