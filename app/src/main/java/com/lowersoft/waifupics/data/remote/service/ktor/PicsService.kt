package com.lowersoft.waifupics.data.remote.service.ktor

import com.lowersoft.waifupics.data.remote.dto.Pic

interface PicsService {
    suspend fun getRandomPic(): Pic
}