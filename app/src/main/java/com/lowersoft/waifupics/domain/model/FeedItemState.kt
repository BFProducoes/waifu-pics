package com.lowersoft.waifupics.domain.model

data class FeedItemState(
    val imageId: Int,
    val imageUrl: String,
    val imagePreviewUrl: String,
    val favoritesCount: Int,
    val isNotSafeForWork: Boolean
)
