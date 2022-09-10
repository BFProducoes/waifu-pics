package com.lowersoft.waifupics.domain.model

sealed interface FeedScreenState {
    object Loading : FeedScreenState
    object Empty : FeedScreenState
    data class Data(val items: List<FeedItem>) : FeedScreenState
}