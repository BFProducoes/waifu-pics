package com.lowersoft.waifupics.data.remote.service.ktor

import android.util.Log
import com.lowersoft.waifupics.common.Constants
import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicsServiceImpl(private val client: HttpClient) : PicsService {

    private val tag = "PicsWebService"

    override suspend fun getRandomPic(): Flow<Request<Pictures>> {
        return flow {
            emit(Request.Loading)
            try {
                emit(Request.Success(client.get { url(Constants.URLs.RANDOM) }))
            } catch (e: Exception) {
                Log.e(tag,
                    e.message
                        ?: "An error occurred while trying to make the random pictures request. See logging for more info. "
                )
                emit(Request.Error(e))
            }
        }
    }
}