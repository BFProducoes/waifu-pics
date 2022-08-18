package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.data.remote.dto.Picture
import com.lowersoft.waifupics.data.remote.service.ktor.PicsServiceImpl
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

class PicsRepositoryKtorImpl(
    private val service: PicsServiceImpl
) : PicsRepository {

    override suspend fun getRandomPicture(): Flow<Request<List<Picture>>> {
        return service.getRandomPic()
    }
}