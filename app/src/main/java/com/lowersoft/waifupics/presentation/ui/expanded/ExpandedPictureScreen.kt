package com.lowersoft.waifupics.presentation.ui.expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lowersoft.waifupics.common.TopBar
import com.lowersoft.waifupics.domain.model.PictureState
import com.lowersoft.waifupics.presentation.ui.feed.FeedViewModel
import com.lowersoft.waifupics.presentation.ui.feed.components.EmptyScreenBox
import com.lowersoft.waifupics.presentation.ui.feed.components.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedPictureScreen(model: FeedViewModel, onBackNavigation: () -> Unit) {

    val selectedItemState: State<PictureState> = model.selectedItemState.collectAsState()

    Scaffold(topBar = { TopBar(text = "Select") }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedItemState.value) {
                is PictureState.Loading -> {
                    Loading()
                }
                is PictureState.Loaded -> {
                    val item = selectedItemState.value as PictureState.Loaded
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.data.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                else -> { EmptyScreenBox() }
            }
        }
    }
}