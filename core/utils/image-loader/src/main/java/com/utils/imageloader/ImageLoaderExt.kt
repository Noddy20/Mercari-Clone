package com.utils.imageloader

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun AsyncImage(
    model: Any?,
    modifier: Modifier = Modifier,
    placeholder: (@Composable () -> Unit)? = null,
    contentDescription: String,
    onStateChange: ((ImageLoaderState) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
) {
    var placeHolderState by remember {
        mutableStateOf(false)
    }
    Box {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = modifier,
            onState = { state ->
                if (placeholder != null) {
                    placeHolderState = state is AsyncImagePainter.State.Loading
                }
                onStateChange?.handleImageLoaderState(state)
            },
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality
        )
        if (placeHolderState) {
            placeholder?.invoke()
        }
    }
}

private fun ((ImageLoaderState) -> Unit).handleImageLoaderState(state: AsyncImagePainter.State) {
    val imageLoaderState = when(state) {
        AsyncImagePainter.State.Empty -> ImageLoaderState.Empty
        is AsyncImagePainter.State.Error -> {
            ImageLoaderState.Error(painter = state.painter, throwable = state.result.throwable)
        }
        is AsyncImagePainter.State.Loading -> {
            ImageLoaderState.Loading(painter = state.painter)
        }
        is AsyncImagePainter.State.Success -> {
            ImageLoaderState.Success(painter = state.painter, drawable = state.result.drawable)
        }
    }
    invoke(imageLoaderState)
}

sealed interface ImageLoaderState {
    /** The current painter being drawn by [ImageLoaderState]. */
    val painter: Painter?

    /** The request has not been started. */
    object Empty : ImageLoaderState {
        override val painter: Painter? get() = null
    }

    /** The request is in-progress. */
    data class Loading(
        override val painter: Painter?,
    ) : ImageLoaderState

    /** The request was successful. */
    data class Success(
        override val painter: Painter,
        val drawable: Drawable?,
    ) : ImageLoaderState

    /** The request failed due to throwable. */
    data class Error(
        override val painter: Painter?,
        val throwable: Throwable?
    ) : ImageLoaderState
}

