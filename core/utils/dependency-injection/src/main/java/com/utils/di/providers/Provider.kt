package com.utils.di.providers

interface Provider<T> {
    fun get(): T
    fun destroy() {}
}

