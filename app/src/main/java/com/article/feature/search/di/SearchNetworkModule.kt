package com.article.feature.search.di

import com.article.feature.search.data.network.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchNetworkModule {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}