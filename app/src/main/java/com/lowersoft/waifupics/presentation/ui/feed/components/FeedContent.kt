package com.lowersoft.waifupics.presentation.ui.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lowersoft.waifupics.domain.model.FeedItem
import com.lowersoft.waifupics.domain.model.FeedScreenState
import com.lowersoft.waifupics.presentation.ui.feed.FeedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedContent(
    model: FeedViewModel,
    randomState: State<FeedScreenState>,
    nsfwState: State<FeedScreenState>,
    oppaiState: State<FeedScreenState>,
    onItemSelected: (FeedItem) -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.background,
            divider = {},
        ) {
            model.pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = MaterialTheme.colorScheme.onBackground,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(count = model.pages.size, state = pagerState) { page ->
            when (page) {
                0 -> PagerContentStateHandler(
                    state = randomState,
                    onItemSelected = onItemSelected
                )
                1 -> PagerContentStateHandler(
                    state = nsfwState,
                    onItemSelected = onItemSelected
                )
                2 -> PagerContentStateHandler(
                    state = oppaiState,
                    onItemSelected = onItemSelected
                )
            }
        }
    }
}