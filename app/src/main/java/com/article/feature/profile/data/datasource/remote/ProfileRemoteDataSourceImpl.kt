package com.article.feature.profile.data.datasource.remote

import com.article.core.common.ApiResultWrapper
import com.article.feature.profile.data.network.api.ProfileApi
import com.article.feature.profile.data.network.model.ProfileResponse

import safeApiCall
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(private val api: ProfileApi) :
    ProfileRemoteDataSource {

    override suspend fun profile(id: Int): ApiResultWrapper<ProfileResponse> =
        safeApiCall { api.profile(id) }

}