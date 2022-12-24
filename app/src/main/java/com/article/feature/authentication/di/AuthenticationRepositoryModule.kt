


import com.article.core.data.AppSharedPreferences
import com.article.feature.authentication.data.database.dao.AuthenticationDao
import com.article.feature.authentication.data.datasource.local.AuthLocalDataSource
import com.article.feature.authentication.data.datasource.local.AuthLocalDataSourceImpl
import com.article.feature.authentication.data.datasource.remote.AuthRemoteDataSource
import com.article.feature.authentication.data.datasource.remote.AuthRemoteDataSourceImpl
import com.article.feature.authentication.data.network.api.AuthenticationApi
import com.article.feature.authentication.data.repository.AuthenticationRepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthenticationRepositoryModule {

    @Binds
    abstract fun bindsAuthLocalDataSource(authLocalDataSourceImpl: AuthLocalDataSourceImpl):
            AuthLocalDataSource

    @Binds
    abstract fun bindsAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl):
            AuthRemoteDataSource

    @Binds
    abstract fun bindsAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    companion object {

        @Provides
        fun provideAuthLocalDataSourceImpl(
            authenticationDao: AuthenticationDao,
            appSharedPreferences: AppSharedPreferences
        ): AuthLocalDataSourceImpl =
            AuthLocalDataSourceImpl(authenticationDao, appSharedPreferences)

        @Provides
        fun provideAuthRemoteDataSourceImpl(authenticationApi: AuthenticationApi):
                AuthRemoteDataSourceImpl = AuthRemoteDataSourceImpl(authenticationApi)

        @Provides
        fun provideAuthenticationRepositoryImpl(
            localDataSource: AuthLocalDataSource,
            remoteDataSource: AuthRemoteDataSource
        ): AuthenticationRepositoryImpl =
            AuthenticationRepositoryImpl(localDataSource, remoteDataSource)
    }
}