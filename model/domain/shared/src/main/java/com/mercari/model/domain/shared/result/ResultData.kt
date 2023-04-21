package com.mercari.model.domain.shared.result

import com.mercari.model.domain.shared.error.FailureResult

sealed interface ResultData<T> {

    data class Success<T>(val data: T) : ResultData<T>

    data class Failure<T>(val failureResult: FailureResult) : ResultData<T>
}