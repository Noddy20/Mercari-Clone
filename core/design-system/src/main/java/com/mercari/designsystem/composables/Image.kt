package com.mercari.designsystem.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.resources.R
import com.utils.imageloader.AsyncImage
import com.utils.imageloader.ImageLoaderState

@Composable
fun MercariBaseImage(
    model: Any?,
    modifier: Modifier = Modifier,
    placeholder: (@Composable () -> Unit)? = null,
    contentDescription: String,
    onStateChange: ((ImageLoaderState) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        onStateChange = onStateChange,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        placeholder = placeholder
    )
}

@Composable
fun MercariProductItemImage(
    url: String,
    modifier: Modifier = Modifier,
    onStateChange: ((ImageLoaderState) -> Unit)? = null
) {
    MercariBaseImage(
        model = url,
        modifier = modifier,
        contentDescription = stringResource(R.string.content_description_product_item_image),
        onStateChange = onStateChange,
        contentScale = ContentScale.Crop,
        placeholder = {
            MercariBaseImage(
                modifier = modifier,
                model = R.drawable.ic_mercari,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.content_description_product_item_image)
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariBaseImage() = MercariTheme {
    MercariBaseImage(
        model = R.drawable.ic_mercari,
        contentDescription = ""
    )
}
