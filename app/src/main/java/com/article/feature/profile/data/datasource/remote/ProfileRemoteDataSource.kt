package com.article.feature.profile.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.profile.data.network.model.ProfileResponse


interface ProfileRemoteDataSource {

    suspend fun profile(id: Int): ApiResultWrapper<ProfileResponse>
}