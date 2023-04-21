package com.mercari.model.data.shared.error

sealed interface ErrorResponse {
    object NoDataFound : ErrorResponse {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    object NoInternet : ErrorResponse {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    class ServerError(val errorData: ErrorData? = null) : ErrorResponse {
        override fun toString(): String {
            return javaClass.simpleName
        }

        data class ErrorData(val title: String, val message: String)
    }

    object ClientError : ErrorResponse {
        override fun toString(): String {
            return javaClass.simpleName
        }
    }
}

//TODO: Remove the toString() once kotlin is upgraded to 1.9.. and replace with data object