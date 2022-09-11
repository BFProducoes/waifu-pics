package com.lowersoft.waifupics.presentation.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.lowersoft.waifupics.common.TopBar
import com.lowersoft.waifupics.domain.model.FeedScreenState
import com.lowersoft.waifupics.presentation.ui.feed.components.FeedContent

@Composable
fun FeedScreen(
    model: FeedViewModel,
    navigateToExpandedScreen: () -> Unit
) {
    val randomState: State<FeedScreenState> = model.randomFeedState.collectAsState()
    val nsfwState: State<FeedScreenState> = model.nsfwFeedState.collectAsState()
    val oppaiState: State<FeedScreenState> = model.oppaiFeedState.collectAsState()

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        topBar = { TopBar(text = "Explore") }
    ) {
        FeedContent(
            model = model,
            randomState = randomState,
            nsfwState = nsfwState,
            oppaiState = oppaiState,
            onItemSelected = {
                model.onFeedItemSelected(it)
                navigateToExpandedScreen()
            }
        )
    }
}