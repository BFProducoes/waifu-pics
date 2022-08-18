package com.lowersoft.waifupics.data.remote.service.retrofit

sealed class Request<out T: Any> {
    data class Success<out T : Any>(val data: T) : Request<T>()
    data class Error(val exception: Throwable) : Request<Nothing>()
    object Loading : Request<Nothing>()
}
