package com.lowersoft.waifupics.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(modifier: Modifier = Modifier, text: String) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(start = 20.dp)
        )
    }
}