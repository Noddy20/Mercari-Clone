package com.ktx.kotlinktx

/**
 *   Map Functions
 */

fun <T, K> Map<T, K?>.getValueOrDefault(mKey: T, default: K?): K? {
    return if (containsKey(mKey)) get(mKey) else default
}

/**
 *   ArrayList
 */

fun <T> List<T>.toArrayList() = ArrayList(this)

fun <T> listOf(elements: List<T>?): List<T> = if (elements.isNullOrEmpty()) emptyList() else ArrayList(elements)

fun <T> arrayListOf(elements: List<T>?): ArrayList<T> = if (elements.isNullOrEmpty()) arrayListOf() else ArrayList(elements)