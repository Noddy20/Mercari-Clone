package com.mercari.designsystem.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.mercari.resources.R

val Typography: Typography
    get() {
        val defaultTypography = Typography(
            defaultFontFamily = FontFamily(Font(R.font.font_averta_regular))
        )
        return defaultTypography.run {
            defaultTypography.copy(
                h1 = h1.copy(fontSize = FontSizeH1Sp),
                h2 = h2.copy(fontSize = FontSizeH2Sp),
                h3 = h3.copy(fontSize = FontSizeH3Sp),
                h4 = h4.copy(fontSize = FontSizeH4Sp),
                h5 = h5.copy(fontSize = FontSizeH5Sp),
                h6 = h6.copy(fontSize = FontSizeH6Sp),
                subtitle1 = subtitle1.copy(fontSize = FontSizeH4Sp),
                subtitle2 = subtitle2.copy(fontSize = FontSizeH5Sp),
                body1 = body1.copy(fontSize = FontSizePSp),
                body2 = body2.copy(fontSize = FontSizeH5Sp),
                button = button.copy(fontSize = FontSizeH6Sp),
                caption = caption.copy(fontSize = FontSizePSp),
                overline = overline.copy(fontSize = FontSizeH6Sp),
            )
        }
    }