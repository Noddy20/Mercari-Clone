package com.ktx.kotlinktx

import kotlin.reflect.KProperty0

inline fun <T> T?.ifNotNull(block: (it: T) -> Unit): T? {
    return if (this == null) null else {
        block(this)
        this
    }
}

inline fun <T> T?.ifNull(block: (it: T?) -> Unit): T? {
    return if (this != null) this else {
        block(null)
        null
    }
}

fun <T> T?.onNull(default: T) = this ?: default