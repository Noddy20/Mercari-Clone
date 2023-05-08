package com.utils.di.providers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class DestroyableProvider<T>(
    lifecycle: Lifecycle? = null,
    private val destroyEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    private val initializer: () -> T
) : Provider<T> {

    private var value: T? = null

    init {
        lifecycle?.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (destroyEvent == event) {
                    value = null
                }
            }
        })
    }

    override fun get(): T {
        return value ?: initializer().also {
            value = it
        }
    }

    override fun destroy() {
        value = null
    }
}

fun <T> destroyableProvider(
    lifecycle: Lifecycle? = null,
    destroyEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    initializer: () -> T
): Provider<T> = DestroyableProvider(lifecycle, destroyEvent, initializer)
