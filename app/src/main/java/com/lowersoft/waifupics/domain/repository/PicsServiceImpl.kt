package com.lowersoft.waifupics.domain.repository

import com.lowersoft.waifupics.common.Constants
import com.lowersoft.waifupics.data.remote.dto.Pic
import com.lowersoft.waifupics.data.remote.service.ktor.PicsService
import io.ktor.client.*
import io.ktor.client.request.*

class PicsServiceImpl(
    private val client: HttpClient
) : PicsService {
    override suspend fun getRandomPic(): Pic {
        return client.get { url(Constants.URLs.RANDOM) }
    }
}