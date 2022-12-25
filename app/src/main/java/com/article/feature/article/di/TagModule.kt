package com.article.feature.article.di
import AppDataBase
import TagHomeLocalDataSourceImpl
import com.article.feature.article.data.database.dao.TagDao
import com.article.feature.article.data.datasource.local.TagHomeLocalDataSource
import com.article.feature.article.data.datasource.remote.TagHomeRemoteDataSource
import com.article.feature.article.data.datasource.remote.TagHomeRemoteDataSourceImpl
import com.article.feature.article.data.newtwork.api.TagApi
import com.article.feature.article.data.repository.TagRepository
import com.article.feature.article.data.repository.TagRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class TagModule {

    @Binds
    abstract fun provideLocalDataSource(impl: TagHomeLocalDataSourceImpl): TagHomeLocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(impl: TagHomeRemoteDataSourceImpl): TagHomeRemoteDataSource

    @Binds
    abstract fun provideTagRepository(impl: TagRepositoryImpl): TagRepository

    companion object {
        @Provides
        fun provideApi(retrofit: Retrofit): TagApi = retrofit.create(TagApi::class.java)

        @Provides
        fun provideTagDao(database: AppDataBase): TagDao = database.tagDao()
    }

}