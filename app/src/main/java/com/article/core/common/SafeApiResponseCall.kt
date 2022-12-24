
import android.util.Log
import com.article.core.common.ApiResultWrapper
import com.article.core.data.ErrorTypeOneResponse
import com.article.core.data.ErrorTypeTwoResponse
import com.google.gson.Gson

import retrofit2.Response

suspend fun <H> safeApiCall(apiCall: suspend () -> Response<H>): ApiResultWrapper<H> {
    return try {
        safeApiResponse(apiCall.invoke())
    } catch (t: Throwable) {
        Log.d("AppLogCheck", "Safe Api Call Exception -> ${t.message}")
        ApiResultWrapper.Failure
    }
}

private fun <M> safeApiResponse(response: Response<M>): ApiResultWrapper<M> {
    val body = response.body()
    val errorBody = response.errorBody()
    return if (response.isSuccessful && body != null) {
        ApiResultWrapper.Success(body)
    } else if (response.code() in (400..401) && errorBody != null) {
        val responseMessage = errorBody.string()
        return try {
            val errorMessage = Gson().fromJson(
                responseMessage, ErrorTypeOneResponse::class.java
            )
            ApiResultWrapper.ErrorData(response.code(), listOf(errorMessage.message))
        } catch (_: Exception) {
            val errorMessages = Gson().fromJson(
                responseMessage, ErrorTypeTwoResponse::class.java
            )
            ApiResultWrapper.ErrorData(
                response.code(), errorMessages.message
            )
        }
    } else {
        ApiResultWrapper.Failure
    }
}