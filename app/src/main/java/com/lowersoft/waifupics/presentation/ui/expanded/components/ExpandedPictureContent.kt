package com.lowersoft.waifupics.presentation.ui.expanded.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.SubcomposeAsyncImage
import com.lowersoft.waifupics.domain.model.FeedItem

@Composable
fun ExpandedPictureContent(
    item: FeedItem,
    isClicked: State<Boolean>,
    onImageClick: () -> Unit
) {

    val dominantColor = Color(item.imageDominantColorHex.toColorInt())

    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .clickable { onImageClick() },
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = dominantColor
    ) {
        SubcomposeAsyncImage(
            model = item.imageUrl,
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = "A sexy anime/manga girl.",
            contentScale = ContentScale.Crop
        )
        if (isClicked.value) {
            ImageOptionsScreen(item = item, dominantColor)
        }
    }
}