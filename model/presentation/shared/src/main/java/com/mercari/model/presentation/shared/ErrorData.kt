package com.mercari.model.presentation.shared

sealed class ErrorData(
    open val allowRetry: Boolean
) {

    fun getTitle(stringResource: (id: Int) -> String): String {
        return when(this) {
            is RemoteErrorData -> title
            is LocalErrorData -> stringResource(titleRes)
        }
    }

    fun getMessage(stringResource: (id: Int) -> String): String {
        return when(this) {
            is RemoteErrorData -> message
            is LocalErrorData -> stringResource(messageRes)
        }
    }

    data class RemoteErrorData(
        val title: String,
        val message: String,
        override val allowRetry: Boolean = true
    ) : ErrorData(allowRetry)

    data class LocalErrorData(
        val titleRes: Int,
        val messageRes: Int,
        override val allowRetry: Boolean = true
    ) : ErrorData(allowRetry)
}