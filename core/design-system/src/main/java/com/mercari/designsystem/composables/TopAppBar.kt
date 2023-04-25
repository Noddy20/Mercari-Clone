package com.mercari.designsystem.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mercari.resources.R
import com.mercari.designsystem.theme.ColorWhite
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Size48Dp
import com.mercari.designsystem.theme.ZeroDp
import com.mercari.designsystem.theme.PaddingXDp

@Composable
fun MercariTopAppBar(
    @StringRes titleRes: Int
) {
    TopAppBar(
        title = {
            MercariMediumIcon(
                modifier = Modifier
                    .width(Size48Dp)
                    .height(Size48Dp)
                    .padding(end = PaddingXDp),
                painter = painterResource(id = R.drawable.ic_mercari),
                contentDescription = stringResource(id = R.string.content_description_mercari_logo),
                tint = Color.Unspecified
            )
            MercariTitleText(text = stringResource(id = titleRes))
        },
        elevation = ZeroDp,
        backgroundColor = MaterialTheme.colors.background
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariTopAppBar() = MercariTheme {
    MercariTopAppBar(R.string.app_name)
}