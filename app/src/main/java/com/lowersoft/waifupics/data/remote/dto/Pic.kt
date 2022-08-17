package com.lowersoft.waifupics.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Pic(
    val images: List<Image>
)