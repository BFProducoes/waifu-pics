package com.lowersoft.waifupics.data.remote.service.retrofit

import com.lowersoft.waifupics.data.remote.dto.Pic
import retrofit2.Response
import retrofit2.http.GET

interface WaifuPicsApi {
    @GET("/random/")
    suspend fun getRandomPic(): Response<Pic>
}