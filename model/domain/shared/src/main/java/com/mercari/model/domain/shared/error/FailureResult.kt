package com.mercari.model.domain.shared.error

sealed interface FailureResult {
    object NoDataFound : FailureResult {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    object NoInternet : FailureResult {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    class ServerError(val errorData: ErrorData? = null) : FailureResult {
        override fun toString(): String {
            return javaClass.simpleName
        }

        data class ErrorData(val title: String, val message: String)
    }

    object ClientError : FailureResult {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }
}

//TODO: Remove the toString() once kotlin is upgraded to 1.9.. and replace with data object