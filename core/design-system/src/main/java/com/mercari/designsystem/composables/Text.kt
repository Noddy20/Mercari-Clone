package com.mercari.designsystem.composables

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.mercari.designsystem.theme.ColorBlackAlpha22
import com.mercari.designsystem.theme.ColorMercariYellow
import com.mercari.designsystem.theme.ColorWhite
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Padding2XDp
import com.mercari.designsystem.theme.Padding3XDp
import com.mercari.designsystem.theme.Padding4XDp
import com.mercari.designsystem.theme.PaddingXDp

const val MERCARI_TEXT_TITLE_MAX_LINE = 3

@Composable
fun MercariBaseText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalTextStyle.current.color,
    fontSize: TextUnit = LocalTextStyle.current.fontSize,
    fontStyle: FontStyle? = LocalTextStyle.current.fontStyle,
    fontWeight: FontWeight? = LocalTextStyle.current.fontWeight,
    fontFamily: FontFamily? = LocalTextStyle.current.fontFamily,
    letterSpacing: TextUnit = LocalTextStyle.current.letterSpacing,
    textDecoration: TextDecoration? = LocalTextStyle.current.textDecoration,
    textAlign: TextAlign? = LocalTextStyle.current.textAlign,
    lineHeight: TextUnit = LocalTextStyle.current.lineHeight,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Composable
fun MercariTitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalTextStyle.current.color,
    textAlign: TextAlign? = LocalTextStyle.current.textAlign,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    @IntRange(from = 1, to = 3) maxLines: Int = MERCARI_TEXT_TITLE_MAX_LINE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    val textStyle = MaterialTheme.typography.h2
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = textStyle.fontSize,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        fontFamily = textStyle.fontFamily,
        letterSpacing = textStyle.letterSpacing,
        textDecoration = textStyle.textDecoration,
        textAlign = textAlign,
        lineHeight = textStyle.lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = textStyle
    )
}

@Composable
fun MercariCaptionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalTextStyle.current.color,
    textAlign: TextAlign? = LocalTextStyle.current.textAlign,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    val textStyle = MaterialTheme.typography.caption
    MercariCommonText(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        textStyle = textStyle
    )
}

@Composable
fun MercariBodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalTextStyle.current.color,
    textAlign: TextAlign? = LocalTextStyle.current.textAlign,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    val textStyle = MaterialTheme.typography.body1
    MercariCommonText(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        textStyle = textStyle
    )
}

@Composable
private fun MercariCommonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalTextStyle.current.color,
    textAlign: TextAlign? = LocalTextStyle.current.textAlign,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    textStyle: TextStyle
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = textStyle.fontSize,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        fontFamily = textStyle.fontFamily,
        letterSpacing = textStyle.letterSpacing,
        textDecoration = textStyle.textDecoration,
        textAlign = textAlign,
        lineHeight = textStyle.lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = textStyle
    )
}

@Composable
fun MercariSoldItemText(
    text: String,
    modifier: Modifier = Modifier
) {
    MercariBodyText(
        text = text,
        textAlign = TextAlign.Center,
        color = ColorWhite,
        modifier = modifier
            .graphicsLayer {
                shadowElevation = PaddingXDp.toPx()
                shape = SoldRibbonShape
                clip = true
            }
            .background(color = ColorMercariYellow)
            .padding(horizontal = Padding4XDp, vertical = PaddingXDp)
            .padding(start = Padding2XDp)
    )
}

@Composable
fun MercariPriceTagText(
    text: String,
    modifier: Modifier = Modifier
) {
    MercariBodyText(
        text = text,
        textAlign = TextAlign.Center,
        color = ColorWhite,
        modifier = modifier
            .padding(bottom = Padding2XDp)
            .graphicsLayer {
                shadowElevation = PaddingXDp.toPx()
                shape = PriceTagShape
                clip = true
            }
            .background(color = ColorBlackAlpha22)
            .padding(horizontal = Padding3XDp, vertical = PaddingXDp)
            .padding(end = Padding2XDp)
    )
}

/**
 *   ----- Previews -----
 */

@Preview(showBackground = true)
@Composable
private fun PreviewMercariText() = MercariTheme {
    MercariBaseText(text = "MercariText")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariTitleText() = MercariTheme {
    MercariTitleText(text = "MercariTitleText")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariCaptionText() = MercariTheme {
    MercariCaptionText(text = "MercariCaptionText")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariBodyText() = MercariTheme {
    MercariBodyText(text = "MercariBodyText")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariBaseText() = MercariTheme {
    MercariCommonText(text = "MercariBaseText", textStyle = MaterialTheme.typography.h5)
}

@Preview(showBackground = true)
@Composable
private fun PreviewSoldItemText() = MercariTheme {
    MercariSoldItemText(text = "SOLD")
}

@Preview(showBackground = true)
@Composable
private fun PreviewMercariPriceTagText() = MercariTheme {
    MercariPriceTagText(text = "200$")
}
