package com.lowersoft.waifupics.domain.model

import com.lowersoft.waifupics.data.remote.dto.Tag

data class FeedItemState(
    val imageId: Int,
    val imageUrl: String,
    val imagePreviewUrl: String,
    val favoritesCount: Int,
    val isNotSafeForWork: Boolean,
    val imageDominantColorHex: String,
    val tags: List<Tag>
)
