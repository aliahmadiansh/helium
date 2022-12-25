package com.article.feature.article.data.repository

import com.article.core.common.ApiResultWrapper
import com.article.feature.article.data.newtwork.model.TageResponse


interface TagRepository {

    suspend fun getTags(): ApiResultWrapper<TageResponse>
}