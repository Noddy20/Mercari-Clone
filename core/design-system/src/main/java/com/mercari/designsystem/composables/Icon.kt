package com.mercari.designsystem.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Size24Dp
import com.mercari.designsystem.theme.Size48Dp
import com.mercari.resources.R

@Composable
fun MercariBaseIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun MercariSmallIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    MercariBaseIcon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.size(Size24Dp),
        tint = tint
    )
}

@Composable
fun MercariMediumIcon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    MercariBaseIcon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.size(Size48Dp),
        tint = tint
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariBaseIcon() = MercariTheme {
    MercariBaseIcon(
        painter = painterResource(id = R.drawable.ic_mercari),
        contentDescription = ""
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariSmallIcon() = MercariTheme {
    MercariMediumIcon(
        painter = painterResource(id = R.drawable.ic_mercari),
        contentDescription = ""
    )
}
