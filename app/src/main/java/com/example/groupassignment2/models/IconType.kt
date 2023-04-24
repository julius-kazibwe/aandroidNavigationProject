package com.example.groupassignment2.models

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconType {
    data class PainterIcon(val painter: Painter) : IconType()
    data class ImageVectorIcon(val imageVector: ImageVector) : IconType()
}


