package com.ktx.corektx

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> mutableStateFlow(value: T) = MutableStateFlow(value)

fun <T> mutableSharedFlow(
    replay: Int = 0,
    extraBufferCapacity: Int = replay,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) = MutableSharedFlow<T>(replay, extraBufferCapacity, onBufferOverflow)


fun <T> Flow<T>.distinctUntilChanged(
    areEquivalent: (old: T, new: T) -> Boolean = { old, new ->
        old == new
    }
): Flow<T> = distinctUntilChanged(areEquivalent)
