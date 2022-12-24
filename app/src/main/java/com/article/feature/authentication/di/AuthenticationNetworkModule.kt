


import com.article.feature.authentication.data.network.api.AuthenticationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationNetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationAPI(retrofit: Retrofit): AuthenticationApi =
        retrofit.create(AuthenticationApi::class.java)
}