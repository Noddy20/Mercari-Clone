package com.mercari.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.mercari.designsystem.theme.ColorMercariYellow
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Size18Dp
import com.mercari.designsystem.theme.ZeroDp

object MercariShapes {

    class SoldRibbonShape : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            return Outline.Generic(
                Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(40f, size.height)
                    close()
                }
            )
        }
    }
}

val SoldRibbonShape: MercariShapes.SoldRibbonShape
    get() = MercariShapes.SoldRibbonShape()

val PriceTagShape: RoundedCornerShape
    get() = RoundedCornerShape(ZeroDp, Size18Dp, Size18Dp, ZeroDp)

@Preview(showBackground = false)
@Composable
private fun PreviewSoldRibbonShape() = MercariTheme {
    Box(modifier = Modifier
        .width(Size18Dp)
        .height(Size18Dp)
        .graphicsLayer {
            shape = SoldRibbonShape
            clip = true
        }
        .background(color = ColorMercariYellow))
}

@Preview(showBackground = false)
@Composable
private fun PreviewPriceTagShape() = MercariTheme {
    Box(modifier = Modifier
        .width(Size18Dp)
        .height(Size18Dp)
        .graphicsLayer {
            shape = PriceTagShape
            clip = true
        }
        .background(color = ColorMercariYellow))
}