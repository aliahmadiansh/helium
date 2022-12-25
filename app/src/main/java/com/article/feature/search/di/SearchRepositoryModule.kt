package com.article.feature.search.di

import com.article.feature.search.data.datasource.remote.SearchRemoteDataSource
import com.article.feature.search.data.datasource.remote.SearchRemoteDataSourceImpl
import com.article.feature.search.data.repository.SearchRepository
import com.article.feature.search.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchRepositoryModule {

    @Binds
    abstract fun bindsSearchRemoteDataSource(impl: SearchRemoteDataSourceImpl):
            SearchRemoteDataSource

    @Binds
    abstract fun bindsSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}