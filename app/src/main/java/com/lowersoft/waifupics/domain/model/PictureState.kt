package com.lowersoft.waifupics.domain.model

sealed interface PictureState {
    object Idle : PictureState
    object Loading : PictureState
    object Error : PictureState
    data class Loaded(val data: FeedItem) : PictureState
}