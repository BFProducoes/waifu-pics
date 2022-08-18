package com.lowersoft.waifupics.data.remote.dto

import com.lowersoft.waifupics.domain.model.FeedItemState
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
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
) {
    fun toFeedItemState() =
        FeedItemState(image_id, url, preview_url, favourites, is_nsfw)
}