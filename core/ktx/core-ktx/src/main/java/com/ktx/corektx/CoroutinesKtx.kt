package com.ktx.corektx

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun Job?.cancelIfActive(): Boolean {
    if (this?.isActive == true) {
        cancel()
        return true
    }
    return false
}

fun <E> SendChannel<E>.trySendThrowing(e: E): ChannelResult<Unit> {
    return trySend(e)
        .onClosed { throw it ?: ClosedSendChannelException("Channel was closed normally") }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

fun LifecycleCoroutineScope.delayOnLifecycle(
    delayInMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job {
    return launch(dispatcher) {
        delay(delayInMillis)
        block()
    }
}

val processLifecycleOwner: LifecycleOwner = ProcessLifecycleOwner.get()

val processLifecycleScope: LifecycleCoroutineScope = processLifecycleOwner.lifecycleScope

fun launchOnProcessLifecycle(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return processLifecycleScope.launch(coroutineContext) {
        block()
    }
}

fun <T> Flow<T>.collectOnProcessLifecycle(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend (T) -> Unit
) {
    launchOnProcessLifecycle(coroutineContext) {
        collect {
            block(it)
        }
    }
}
