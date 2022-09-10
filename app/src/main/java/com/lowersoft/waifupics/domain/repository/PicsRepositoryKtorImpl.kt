package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.common.Constants
import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.ktor.PicsServiceImpl
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import kotlinx.coroutines.flow.Flow

class PicsRepositoryKtorImpl(
    private val service: PicsServiceImpl
) : PicsRepository {

    override suspend fun getWaifuPicList(): Flow<Request<Pictures>> {
        return service.getRequest(Constants.URLs.WAIFU)
    }

    override suspend fun getNsfwPicList(): Flow<Request<Pictures>> {
        return service.getRequest(Constants.URLs.NSFW)
    }

    override suspend fun getOppaiPicList(): Flow<Request<Pictures>> {
        return service.getRequest(Constants.URLs.OPPAI)
    }
}