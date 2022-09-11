package com.lowersoft.waifupics.presentation.ui.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lowersoft.waifupics.domain.model.FeedItem

@Composable
fun PagerItem(
    item: FeedItem,
    onItemClick: (FeedItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .size(160.dp)
            .clickable { onItemClick(item) },
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Box {
            SubcomposeAsyncImage(
                model = item.imageUrl,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.Center),
                            color = Color.Black,
                            strokeWidth = 3.dp
                        )
                    }
                },
                contentDescription = "A sexy anime/manga girl.",
                contentScale = ContentScale.Crop
            )
        }
    }
}