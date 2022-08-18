package com.lowersoft.waifupics.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.lowersoft.waifupics.domain.model.FeedItemState
import com.lowersoft.waifupics.domain.model.FeedScreenState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(model: FeedViewModel = getViewModel()) {


    val state = model.feedState.collectAsState()

    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {

        when (state.value) {
            is FeedScreenState.Data -> FeedPager(items = (state.value as FeedScreenState.Data).items){}
            FeedScreenState.Empty -> EmptyPager()
            FeedScreenState.Loading -> Loading()
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun FeedPager(
    items: List<FeedItemState>,
    onLikeClick: (String) -> Unit
) {
    val pagerState = rememberPagerState()

    VerticalPager(
        count = items.size,
        state = pagerState
    ) { page ->
        PagerItem(
            itemState = items[page],
            isSelected = page == pagerState.currentPage,
            onLikeClick = onLikeClick
        )
    }
}

@Composable
private fun PagerItem(
    itemState: FeedItemState,
    isSelected: Boolean,
    onLikeClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {

        PictureListItem(
            imageUrl = itemState.imageUrl,
            imageDescription = "waifupic",
        )
    }
}

@Composable
fun PictureListItem(imageUrl: String, imageDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = imageDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )
}

@Composable
private fun EmptyPager() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "EMPTY",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = Color.White
        )
    }
}