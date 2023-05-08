package com.ktx.androidktx.compose

import com.ktx.corektx.mutableSharedFlow
import com.ktx.corektx.throttleFirst
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val VIEW_CLICK_THROTTLE_FIRST = 500L

fun <T : Any> coroutineClicks(
    coroutineScope: CoroutineScope,
    throttle: Long = VIEW_CLICK_THROTTLE_FIRST,
    onClick: (T) -> Unit
): (T) -> Unit {
    val flow = mutableSharedFlow<T>()
    coroutineScope.launch {
        flow.throttleFirst(throttle).collectLatest {
            onClick(it)
        }
    }
    return fun(data: T) {
        coroutineScope.launch {
            flow.emit(data)
        }
    }
}

fun coroutineClick(
    coroutineScope: CoroutineScope,
    throttle: Long = VIEW_CLICK_THROTTLE_FIRST,
    onClick: () -> Unit
): () -> Unit {
    val flow = mutableSharedFlow<Unit>()
    coroutineScope.launch {
        flow.throttleFirst(throttle).collectLatest {
            onClick()
        }
    }
    return fun() {
        coroutineScope.launch {
            flow.emit(Unit)
        }
    }
}
