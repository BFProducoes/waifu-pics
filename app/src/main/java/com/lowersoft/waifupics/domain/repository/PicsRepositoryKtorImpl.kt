package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.ktor.PicsServiceImpl
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

class PicsRepositoryKtorImpl(
    private val service: PicsServiceImpl
) : PicsRepository {

    override suspend fun getRandomPicture(): Flow<Request<Pictures>> {
        return service.getRandomPic()
    }
}