package com.utils.di.providers

import com.ktx.corektx.cancelIfActive
import com.ktx.corektx.launchOnProcessLifecycle
import com.logs.logger.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

class AsyncSingletonProvider<T> internal constructor(
    private val tag: String,
    dispatcher: CoroutineDispatcher,
    private val initializer: () -> T
): Provider<T> {

    @Volatile
    private var value: T? = null

    private var initJob: Job? = null

    init {
        Logger.d("initialize $tag Async ${System.nanoTime()}")
        initJob = launchOnProcessLifecycle(dispatcher) {
            val initialized = initializer()
            if (value == null) {
                value = initialized
            }
            initJob = null
            Logger.d("initialized $tag Async ${System.nanoTime()}")
        }
    }

    @Synchronized
    override fun get(): T {
        if (value == null) {
            initJob.cancelIfActive()
            initJob = null
        }
        Logger.d("get $tag $value ${System.nanoTime()}")
        return value ?: initializer().also {
            value = it
            Logger.d("initialized $tag Sync")
        }
    }
}

fun <T> asyncSingletonProvider(
    tag: String = "",
    dispatcher: CoroutineDispatcher,
    initializer: () -> T
): Provider<T> = AsyncSingletonProvider(tag, dispatcher, initializer)
