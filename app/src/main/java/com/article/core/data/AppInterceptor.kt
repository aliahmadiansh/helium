

import com.article.core.data.AppSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(private val appSharedPreferences: AppSharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request()
            .newBuilder()
            .addHeader("Authorization", appSharedPreferences.getToken() ?: "")
            .build())
}