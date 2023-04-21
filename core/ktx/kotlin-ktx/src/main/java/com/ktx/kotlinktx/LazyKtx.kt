package com.ktx.kotlinktx

fun <T> lazyNonSync(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE) { initializer() }