package com.lowersoft.waifupics.presentation.ui.feed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.lowersoft.waifupics.domain.model.FeedItemState
import com.lowersoft.waifupics.domain.model.FeedScreenState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(model: FeedViewModel = getViewModel()) {


    val state = model.feedState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        floatingActionButton = { LikeButton() }
    ) {
        when (state.value) {
            is FeedScreenState.Data -> FeedPager(items = (state.value as FeedScreenState.Data).items) {}
            FeedScreenState.Empty -> EmptyPager()
            FeedScreenState.Loading -> Loading()
        }
    }
}

@Composable
private fun LikeButton() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(
            context,
            "The image was sent to your gallery",
            Toast.LENGTH_SHORT
        ).show()
    }, shape = CircleShape) {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite button")
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
            .fillMaxSize(),
    ) {
        PictureListItem(
            imageUrl = itemState.imageUrl,
            imageDescription = "waifupic",
            backgroundColor = Color(itemState.imageDominantColorHex.toColorInt())
        )
    }
}

@Composable
fun PictureListItem(imageUrl: String, imageDescription: String, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = imageDescription,
                contentScale = ContentScale.Inside,
            )
        }
    }
}

@Composable
private fun EmptyPager() {
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
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}