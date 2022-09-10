package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

interface PicsRepository {

    suspend fun getWaifuPicList(): Flow<Request<Pictures>>

    suspend fun getNsfwPicList(): Flow<Request<Pictures>>

    suspend fun getOppaiPicList(): Flow<Request<Pictures>>

}