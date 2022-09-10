package com.lowersoft.waifupics.presentation.navigation

sealed class Screens(val route: String) {
    object Feed : Screens("feed")
    object ExpandedPicture : Screens("expanded")
}
