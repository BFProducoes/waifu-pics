package com.lowersoft.waifupics.data.remote.service.ktor

import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

interface PicsService {
    suspend fun getRandomPic(): Flow<Request<Pictures>>
}