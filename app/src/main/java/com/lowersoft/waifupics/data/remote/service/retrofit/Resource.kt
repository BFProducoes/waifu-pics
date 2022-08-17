package com.lowersoft.waifupics.data.remote.service.retrofit

sealed class Resource<out T: Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
