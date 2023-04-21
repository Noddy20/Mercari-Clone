package com.mercari.model.data.shared.result

import com.mercari.model.data.shared.error.ErrorResponse

sealed class ResponseResult<out T> {

    class Success<T>(val data: T) : ResponseResult<T>() {
        override fun toString(): String {
            return ResponseResult::class.simpleName +
                    "[" + javaClass.simpleName +
                    "[" + data.toString() + "]" +
                    "]"
        }
    }

    class Failure<T>(val errorResponse: ErrorResponse) : ResponseResult<T>() {
        override fun toString(): String {
            return ResponseResult::class.simpleName +
                    "[" + javaClass.simpleName +
                    "[" + errorResponse.toString() + "]" +
                    "]"
        }
    }
}