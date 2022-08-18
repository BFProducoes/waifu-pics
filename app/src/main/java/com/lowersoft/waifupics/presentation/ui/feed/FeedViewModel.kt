package com.lowersoft.waifupics.presentation.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import com.lowersoft.waifupics.domain.model.FeedItemState
import com.lowersoft.waifupics.domain.model.FeedScreenState
import com.lowersoft.waifupics.domain.repository.PicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: PicsRepository) : ViewModel() {

    private val _feedState = MutableStateFlow<FeedScreenState>(FeedScreenState.Loading)
    val feedState: MutableStateFlow<FeedScreenState> get() = _feedState

    init {
        viewModelScope.launch {
            repository.getRandomPicture().collect { result ->
                _feedState.value = when(result) {
                    is Request.Success -> {
                        val stateList = mutableListOf<FeedItemState>().apply {
                            result.data.images.forEach {
                                add(it.toFeedItemState())
                            }
                        }
                        FeedScreenState.Data(stateList)
                    }
                    is Request.Error -> FeedScreenState.Empty
                    Request.Loading -> FeedScreenState.Loading
                }
            }
        }
    }
}