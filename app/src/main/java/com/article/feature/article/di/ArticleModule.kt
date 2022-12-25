package com.article.feature.article.di
import AppDataBase
import com.article.feature.article.data.database.dao.ArticleDao
import com.article.feature.article.data.database.dao.BookmarkDao
import com.article.feature.article.data.datasource.local.ArticleLocalDataSource
import com.article.feature.article.data.datasource.local.ArticleLocalDataSourceImpl
import com.article.feature.article.data.datasource.remote.ArticleRemoteDataSource
import com.article.feature.article.data.datasource.remote.ArticleRemoteDataSourceImpl
import com.article.feature.article.data.newtwork.api.ArticleApi
import com.article.feature.article.data.repository.ArticleRepository
import com.article.feature.article.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleModule {

    @Binds
    abstract fun provideLocalDataSource(impl: ArticleLocalDataSourceImpl): ArticleLocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(impl: ArticleRemoteDataSourceImpl): ArticleRemoteDataSource

    @Binds
    abstract fun provideArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository

    companion object {
        @Provides
        fun provideApi(retrofit: Retrofit): ArticleApi = retrofit.create(ArticleApi::class.java)

        @Provides
        fun provideArticleDao(database: AppDataBase): ArticleDao = database.articleDao()

        @Provides
        fun provideBookmarkDao(database: AppDataBase): BookmarkDao = database.bookmarkDao()
    }
}