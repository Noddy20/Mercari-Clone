package com.mercari.designsystem.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable

val shapes: Shapes
    @Composable get() {
        val shapes = MaterialTheme.shapes
        return shapes.copy(
            small = shapes.small.copy(all = CornerSize(ShapeCornerSmallDp)),
            medium = shapes.small.copy(all = CornerSize(ShapeCornerMediumDp)),
            large = shapes.small.copy(all = CornerSize(ShapeCornerLargeDp))
        )
    }