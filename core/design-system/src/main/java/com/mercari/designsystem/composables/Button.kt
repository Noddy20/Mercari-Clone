package com.mercari.designsystem.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.ktx.androidktx.compose.VIEW_CLICK_THROTTLE_FIRST
import com.ktx.androidktx.compose.coroutineClick
import com.mercari.designsystem.theme.ColorMercariBlue
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Size36Dp
import com.mercari.designsystem.theme.Size48Dp

@Composable
fun MercariBaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    clickThrottle: Long = VIEW_CLICK_THROTTLE_FIRST,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = ColorMercariBlue),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Button(
        onClick = coroutineClick(
            coroutineScope = coroutineScope,
            throttle = clickThrottle,
            onClick = onClick
        ),
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun MercariMediumButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    clickThrottle: Long = VIEW_CLICK_THROTTLE_FIRST,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    MercariBaseButton(
        modifier = modifier
            .height(Size48Dp),
        clickThrottle = clickThrottle,
        enabled = enabled,
        interactionSource = interactionSource,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        MercariBodyText(text = text, modifier = modifierText)
    }
}

@Composable
fun MercariSmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    clickThrottle: Long = VIEW_CLICK_THROTTLE_FIRST,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    MercariBaseButton(
        modifier = modifier
            .height(Size36Dp),
        clickThrottle = clickThrottle,
        enabled = enabled,
        interactionSource = interactionSource,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        MercariBodyText(text = text, modifier = modifierText)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariButton() = MercariTheme {
    MercariBaseButton(onClick = {}) {
        MercariBodyText(text = "MercariButton")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariMediumButton() = MercariTheme {
    MercariMediumButton(onClick = {}, text = "MercariMediumButton")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariSmallButton() = MercariTheme {
    MercariSmallButton(onClick = {}, text = "MercariSmallButton")
}
