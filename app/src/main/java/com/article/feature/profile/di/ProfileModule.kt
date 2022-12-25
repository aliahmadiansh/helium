package com.article.feature.profile.di


import AppDataBase
import com.article.feature.profile.data.database.dao.MyArticleDao
import com.article.feature.profile.data.datasource.local.BookmarkLocalDataSource
import com.article.feature.profile.data.datasource.local.BookmarkLocalDataSourceImpl
import com.article.feature.profile.data.datasource.local.ProfileLocalDataSource
import com.article.feature.profile.data.datasource.local.ProfileLocalDataSourceImpl
import com.article.feature.profile.data.datasource.remote.ProfileRemoteDataSource
import com.article.feature.profile.data.datasource.remote.ProfileRemoteDataSourceImpl
import com.article.feature.profile.data.network.api.ProfileApi
import com.article.feature.profile.data.repository.ProfileRepository
import com.article.feature.profile.data.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    abstract fun providerRemoteDataSource(impl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

    @Binds
    abstract fun providerLocalDataSource(impl: ProfileLocalDataSourceImpl): ProfileLocalDataSource

    @Binds
    abstract fun providerBookmarkLocalDataSource(impl: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource

    @Binds
    abstract fun providerRepository(impl: ProfileRepositoryImpl): ProfileRepository

    companion object {

        @Provides
        fun provideApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)

        @Provides
        fun provideMyArticleDao(database: AppDataBase): MyArticleDao = database.myArticleDao()
    }
}