package com.mercari.designsystem.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Padding2XDp
import com.mercari.designsystem.theme.Padding4XDp
import com.mercari.resources.R

val ERROR_LOTTIE_SIZE: Dp
    get() = 220.dp

@Composable
fun MercariError(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    allowRetry: Boolean,
    onRetryClick: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_cat_error))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier
                .width(ERROR_LOTTIE_SIZE)
                .height(ERROR_LOTTIE_SIZE),
            composition = composition,
            progress = { progress }
        )

        MercariTitleText(
            modifier = Modifier
                .padding(Padding2XDp),
            text = title,
            textAlign = TextAlign.Center
        )

        MercariBodyText(
            modifier = Modifier
                .padding(Padding2XDp),
            text = message,
            textAlign = TextAlign.Center
        )

        if (allowRetry) {
            MercariSmallButton(
                modifier = Modifier.padding(top = Padding4XDp),
                modifierText = Modifier.padding(horizontal = Padding2XDp),
                onClick = { onRetryClick() },
                text = stringResource(id = R.string.action_retry)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariError() = MercariTheme {
    MercariError(
        Modifier,
        "Error",
        "Something went wrong, try again! Something went wrong, try again! Something went wrong, try again!",
        true
    ) {
        // Retry
    }
}
