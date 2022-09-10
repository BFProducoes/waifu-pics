package com.lowersoft.waifupics.presentation.ui.feed.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lowersoft.waifupics.domain.model.FeedItem
import com.lowersoft.waifupics.domain.model.FeedScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedPager(
    items: List<FeedItem>,
    onItemClick: (FeedItem) -> Unit
) {
    LazyVerticalGrid(cells = GridCells.Adaptive(160.dp)) {
        items(items) {
            PagerItem(item = it, onItemClick = onItemClick)
        }
    }
}

@Composable
fun PagerContentStateHandler(
    state: State<FeedScreenState>,
    onItemSelected: (FeedItem) -> Unit
) {
    when (state.value) {
        is FeedScreenState.Data -> FeedPager(
            items = (state.value as FeedScreenState.Data).items,
            onItemClick = onItemSelected
        )
        FeedScreenState.Empty -> EmptyScreenBox()
        FeedScreenState.Loading -> Loading()
    }
}

@Composable
fun EmptyScreenBox() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Oops! There's no pictures here yet.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = Color.Black,
            strokeWidth = 8.dp
        )
    }
}