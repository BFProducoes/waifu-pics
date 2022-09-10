package com.lowersoft.waifupics.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lowersoft.waifupics.presentation.ui.expanded.ExpandedPictureScreen
import com.lowersoft.waifupics.presentation.ui.feed.FeedScreen
import com.lowersoft.waifupics.presentation.ui.feed.FeedViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun startNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Feed.route
) {
    val feedViewModel: FeedViewModel = getViewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Feed.route) {
            FeedScreen(model = feedViewModel, navigateToExpandedScreen = {
                navController.navigate(Screens.ExpandedPicture.route)
            })
        }
        composable(Screens.ExpandedPicture.route) {
            ExpandedPictureScreen(model = feedViewModel, onBackNavigation = {
                navController.popBackStack()
            })
        }
    }
}