

import com.article.feature.authentication.data.database.dao.AuthenticationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationDataBaseModule {

    @Provides
    fun provideAuthenticationDAO(appDataBase: AppDataBase): AuthenticationDao =
        appDataBase.authenticationDao()
}