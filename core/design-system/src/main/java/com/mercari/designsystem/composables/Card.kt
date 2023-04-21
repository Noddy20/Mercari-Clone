package com.mercari.designsystem.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mercari.designsystem.theme.CardElevationDp
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Padding2XDp

@Composable
fun MercariBaseCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(corner = CornerSize(Padding2XDp)),
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    border: BorderStroke? = null,
    elevation: Dp = CardElevationDp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        content = content
    )
}

/**
 *   ----- Previews -----
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariCard() = MercariTheme {
    MercariBaseCard(
        modifier = Modifier.size(200.dp),
        content = {}
    )
}