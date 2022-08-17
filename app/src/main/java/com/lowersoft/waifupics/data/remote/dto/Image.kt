package com.lowersoft.waifupics.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val dominant_color: String,
    val extension: String,
    val favourites: Int,
    val file: String,
    val height: Int,
    val image_id: Int,
    val is_nsfw: Boolean,
    val preview_url: String,
    val source: String,
    val tags: List<Tag>,
    val uploaded_at: String,
    val url: String,
    val width: Int
)