package com.mercari.presentation.shared.transformations

import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.resources.R

interface ErrorDataTransformations : Transformations<FailureResult, ErrorData>

internal class ErrorDataTransformationsImpl : ErrorDataTransformations {

    override fun transformTo(from: FailureResult): ErrorData {
        return when(from) {
            is FailureResult.NoDataFound -> {
                ErrorData.LocalErrorData(
                    titleRes = R.string.title_no_data_found,
                    messageRes = R.string.message_no_data_found,
                    allowRetry = false
                )
            }
            is FailureResult.NoInternet -> {
                ErrorData.LocalErrorData(
                    titleRes = R.string.title_offline,
                    messageRes = R.string.message_offline,
                    allowRetry = true
                )
            }
            is FailureResult.ClientError -> {
                ErrorData.LocalErrorData(
                    titleRes = R.string.title_error,
                    messageRes = R.string.message_generic_error,
                    allowRetry = true
                )
            }
            is FailureResult.ServerError -> {
                val title = from.errorData?.title
                val message = from.errorData?.message
                if (!title.isNullOrBlank() && !message.isNullOrBlank()) {
                    ErrorData.RemoteErrorData(
                        title = title,
                        message = message,
                        allowRetry = true
                    )
                } else {
                    ErrorData.LocalErrorData(
                        titleRes = R.string.title_error,
                        messageRes = R.string.message_generic_error,
                        allowRetry = true
                    )
                }
            }
        }
    }
}