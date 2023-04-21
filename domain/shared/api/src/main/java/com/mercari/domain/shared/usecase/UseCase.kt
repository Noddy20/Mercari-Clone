package com.mercari.domain.shared.usecase

import kotlinx.coroutines.flow.Flow

interface SuspendableUseCase<T> {
    suspend operator fun invoke(): T
}

interface FlowableUseCase<T> {
    suspend operator fun invoke(): Flow<T>
}