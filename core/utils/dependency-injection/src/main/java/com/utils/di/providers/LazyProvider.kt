package com.utils.di.providers

class LazyProvider<T> internal constructor(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    initializer: () -> T
): Provider<T> {

    private val lazy by lazy(mode, initializer)

    override fun get(): T = lazy

}

fun <T> lazyProvider(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    initializer: () -> T
): Provider<T> = LazyProvider(mode, initializer)