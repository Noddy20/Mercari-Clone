package com.ktx.kotlinktx

val String.Companion.Empty
    get() = ""

fun String?.blankToNull() = if (this.isNullOrBlank()) null else this

inline fun String?.ifNotNullOrBlank(block: (it: String) -> Unit): String? {
    return if (this.isNullOrBlank()) this else {
        block(this)
        this
    }
}

inline fun String?.ifNullOrBlank(block: (it: String?) -> Unit): String? {
    return if (!this.isNullOrBlank()) this else {
        block(this)
        this
    }
}