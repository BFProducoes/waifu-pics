package com.lowersoft.waifupics.presentation.ui.feed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.lowersoft.waifupics.domain.model.FeedItemState
import com.lowersoft.waifupics.domain.model.FeedScreenState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(model: FeedViewModel = getViewModel()) {


    val state = model.feedState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.background,
                elevation = 0.dp
            ) {
                Text(
                    text = "Explore",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
            }
        }
    ) {
        when (state.value) {
            is FeedScreenState.Data -> FeedPager(items = (state.value as FeedScreenState.Data).items)
            FeedScreenState.Empty -> EmptyPager()
            FeedScreenState.Loading -> Loading()
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun FeedPager(
    items: List<FeedItemState>
) {
    LazyColumn {
        items(items) {
            PagerItem(itemState = it)
        }
    }
}

@Composable
private fun PagerItem(
    itemState: FeedItemState
) {

    val isFocused = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .size(160.dp)
            .onFocusEvent { isFocused.value = !isFocused.value },
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(itemState.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                AnimatedVisibility(visible = isFocused.value) {
                    Column {
                        Text(
                            text = itemState.tags.first().name,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = itemState.tags.first().description,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }
                }
            }
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
            color = Color.Cyan,
            strokeWidth = 8.dp
        )
    }
}