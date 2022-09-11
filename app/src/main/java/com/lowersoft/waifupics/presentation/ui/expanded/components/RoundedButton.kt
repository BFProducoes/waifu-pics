package com.lowersoft.waifupics.presentation.ui.expanded.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.lowersoft.waifupics.presentation.ui.theme.RoundedButtonShape

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    iconColor: Color
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedButtonShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Icon(
            imageVector = icon,
            tint = iconColor,
            contentDescription = null
        )
    }
}