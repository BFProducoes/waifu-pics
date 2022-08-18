package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

interface PicsRepository {

    suspend fun getRandomPicture(): Flow<Request<Pictures>>

}