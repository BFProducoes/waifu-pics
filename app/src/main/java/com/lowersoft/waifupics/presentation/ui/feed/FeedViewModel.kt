package com.lowersoft.waifupics.presentation.ui.feed

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lowersoft.waifupics.data.remote.dto.Pictures
import com.lowersoft.waifupics.data.remote.service.retrofit.Request
import com.lowersoft.waifupics.domain.model.FeedItem
import com.lowersoft.waifupics.domain.model.FeedScreenState
import com.lowersoft.waifupics.domain.model.PictureState
import com.lowersoft.waifupics.domain.repository.PicsRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.OutputStream

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

    private fun saveImageOnGallery(context: Context, bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        context.contentResolver?.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            fos = imageUri?.let { resolver.openOutputStream(it) }
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    fun onLikeClick() {

    }

    fun onDownloadClick(context: Context, saveBitmapCallback: () -> Bitmap) {
        MainScope().launch {
            saveImageOnGallery(context, saveBitmapCallback.invoke())
        }
    }

    fun onShareClick() {

    }
}