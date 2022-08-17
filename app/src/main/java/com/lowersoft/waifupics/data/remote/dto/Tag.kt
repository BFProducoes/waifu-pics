package com.lowersoft.waifupics.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val description: String,
    val is_nsfw: Boolean,
    val name: String,
    val tag_id: Int
)