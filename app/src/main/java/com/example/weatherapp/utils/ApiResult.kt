package com.example.weatherapp.utils

sealed class ApiResult<T>(data: T? = null, error: Throwable? = null) {
    data class Success<T>(val data: T) : ApiResult<T>(data)
    data class Error<T>(val error: Throwable) : ApiResult<T>(data = null, error)
    class Loading<T> : ApiResult<T>()
}