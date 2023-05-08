package com.mercari.designsystem.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.mercari.resources.R

val TypographyLight: Typography
    get() {
        val defaultTypography = Typography(
            defaultFontFamily = FontFamily(Font(R.font.font_averta_regular))
        )
        return defaultTypography.run {
            defaultTypography.copy(
                h1 = h1.copy(fontSize = FontSizeH1Sp, color = ColorFontTitleDark),
                h2 = h2.copy(fontSize = FontSizeH2Sp, color = ColorFontTitleDark),
                h3 = h3.copy(fontSize = FontSizeH3Sp, color = ColorFontTitleDark),
                h4 = h4.copy(fontSize = FontSizeH4Sp, color = ColorFontTitleDark),
                h5 = h5.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleDark),
                h6 = h6.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleDark),
                subtitle1 = subtitle1.copy(fontSize = FontSizeH4Sp, color = ColorFontBodyDark),
                subtitle2 = subtitle2.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleDark),
                body1 = body1.copy(fontSize = FontSizePSp, color = ColorFontBodyDark),
                body2 = body2.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleDark),
                button = button.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleDark),
                caption = caption.copy(fontSize = FontSizePSp, color = ColorFontTitleDark),
                overline = overline.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleDark)
            )
        }
    }

val TypographyDark: Typography
    get() {
        val defaultTypography = Typography(
            defaultFontFamily = FontFamily(Font(R.font.font_averta_regular))
        )
        return defaultTypography.run {
            defaultTypography.copy(
                h1 = h1.copy(fontSize = FontSizeH1Sp, color = ColorFontTitleLight),
                h2 = h2.copy(fontSize = FontSizeH2Sp, color = ColorFontTitleLight),
                h3 = h3.copy(fontSize = FontSizeH3Sp, color = ColorFontTitleLight),
                h4 = h4.copy(fontSize = FontSizeH4Sp, color = ColorFontTitleLight),
                h5 = h5.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleLight),
                h6 = h6.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleLight),
                subtitle1 = subtitle1.copy(fontSize = FontSizeH4Sp, color = ColorFontBodyLight),
                subtitle2 = subtitle2.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleLight),
                body1 = body1.copy(fontSize = FontSizePSp, color = ColorFontBodyLight),
                body2 = body2.copy(fontSize = FontSizeH5Sp, color = ColorFontTitleLight),
                button = button.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleLight),
                caption = caption.copy(fontSize = FontSizePSp, color = ColorFontTitleLight),
                overline = overline.copy(fontSize = FontSizeH6Sp, color = ColorFontTitleLight)
            )
        }
    }
