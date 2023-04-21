package com.ktx.androidktx.compose

import androidx.compose.ui.graphics.Color


inline val Long.color: Color get() = Color(color = this)