package com.lowersoft.waifupics.presentation.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import com.lowersoft.waifupics.domain.model.FeedItem
import com.lowersoft.waifupics.domain.model.FeedScreenState
import com.lowersoft.waifupics.domain.model.PictureState
import com.lowersoft.waifupics.domain.repository.PicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: PicsRepository) : ViewModel() {

    private val _randomFeedState = MutableStateFlow<FeedScreenState>(FeedScreenState.Loading)
    val randomFeedState: StateFlow<FeedScreenState> get() = _randomFeedState

    private val _nsfwFeedState = MutableStateFlow<FeedScreenState>(FeedScreenState.Loading)
    val nsfwFeedState: StateFlow<FeedScreenState> get() = _nsfwFeedState

    private val _oppaiFeedState = MutableStateFlow<FeedScreenState>(FeedScreenState.Loading)
    val oppaiFeedState: StateFlow<FeedScreenState> get() = _oppaiFeedState

    private val _selectedItemState = MutableStateFlow<PictureState>(PictureState.Loading)
    val selectedItemState: StateFlow<PictureState> get() = _selectedItemState

    val pages = listOf(
        "Waifu",
        "NSFW",
        "Oppai"
    )

    init {
        viewModelScope.launch {
            repository.run {
                getWaifuPicList().collect {
                    _randomFeedState.value = getStateFromResponse(it)
                }

                getNsfwPicList().collect {
                    _nsfwFeedState.value = getStateFromResponse(it)
                }

                getOppaiPicList().collect {
                    _oppaiFeedState.value = getStateFromResponse(it)
                }
            }
        }
    }

    private fun getStateFromResponse(result: Request<Pictures>) = when (result) {
        is Request.Success -> {
            val stateList = mutableListOf<FeedItem>().apply {
                result.data.images.forEach {
                    add(it.toFeedItemState())
                }
            }
            FeedScreenState.Data(stateList)
        }
        is Request.Error -> FeedScreenState.Empty
        Request.Loading -> FeedScreenState.Loading
    }

    fun onFeedItemSelected(item: FeedItem) {
        _selectedItemState.value = PictureState.Loaded(item)
    }
}