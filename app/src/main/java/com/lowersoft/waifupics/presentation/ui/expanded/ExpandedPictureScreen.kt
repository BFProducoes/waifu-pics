package com.lowersoft.waifupics.presentation.ui.expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lowersoft.waifupics.common.TopBar
import com.lowersoft.waifupics.domain.model.PictureState
import com.lowersoft.waifupics.presentation.ui.expanded.components.ExpandedPictureContent
import com.lowersoft.waifupics.presentation.ui.feed.FeedViewModel
import com.lowersoft.waifupics.presentation.ui.feed.components.EmptyScreenBox
import com.lowersoft.waifupics.presentation.ui.feed.components.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedPictureScreen(model: FeedViewModel, onBackNavigation: () -> Unit) {

    val selectedItemState: State<PictureState> = model.selectedItemState.collectAsState()
    val isClicked = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(topBar = { TopBar(text = "Visualize") }) {
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
                    ExpandedPictureContent(
                        item = item.data,
                        isClicked = isClicked,
                        onImageClick = { isClicked.value = !isClicked.value },
                        onLikeClick = { model.onLikeClick() },
                        onDownloadClick = { saveBitmapCallback ->
                            model.onDownloadClick(
                                context,
                                saveBitmapCallback
                            )
                        },
                        onShareClick = { model.onShareClick() }
                    )
                }
                else -> {
                    EmptyScreenBox()
                }
            }
        }
    }
}