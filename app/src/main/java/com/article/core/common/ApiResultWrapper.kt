package com.article.core.common

sealed interface ApiResultWrapper<out T> {
    data class Success<T>(val response: T) : ApiResultWrapper<T>
    data class ErrorData(val code: Int, val message: List<String>) : ApiResultWrapper<Nothing>
    object Failure : ApiResultWrapper<Nothing>
}