package com.ktx.kotlinktx

operator fun <T> T.invoke(block: T.() -> Unit) = block()