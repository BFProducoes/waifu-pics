package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.data.remote.dto.Pic
import com.lowersoft.waifupics.data.remote.service.retrofit.Resource
import kotlinx.coroutines.flow.Flow

interface PicsRepository {

    suspend fun getRandomPicture(): Flow<Resource<Pic>>

}