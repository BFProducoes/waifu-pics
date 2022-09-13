package com.lowersoft.waifupics.presentation.ui.expanded.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lowersoft.waifupics.domain.model.FeedItem

@Composable
fun ImageOptionsScreen(
    item: FeedItem,
    dominantColor: Color,
    onLikeClick: () -> Unit,
    onShareClick: () -> Unit,
    onDownloadClick: () -> Unit,
    saveBitmapCallback: () -> Bitmap
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dominantColor.let {
                Color(
                    it.red,
                    it.green,
                    it.blue,
                    alpha = .9f
                )
            }), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoundedButton(
                onClick = onLikeClick,
                icon = Icons.Filled.Favorite,
                iconColor = dominantColor
            )
            RoundedButton(
                onClick = onDownloadClick,
                icon = Icons.Filled.KeyboardArrowDown,
                iconColor = dominantColor
            )
            RoundedButton(
                onClick = onShareClick,
                icon = Icons.Filled.Share,
                iconColor = dominantColor
            )
        }
        Text(text = "Tags:", modifier = Modifier.padding(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            item.tags.forEach {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = it.name,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}