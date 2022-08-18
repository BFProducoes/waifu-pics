package com.lowersoft.waifupics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lowersoft.waifupics.presentation.ui.feed.FeedScreen
import com.lowersoft.waifupics.presentation.ui.theme.WaifuPicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaifuPicsTheme {
                FeedScreen()
            }
        }
    }
}