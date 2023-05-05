package com.mercari.presentation.shared.transformations

import androidx.annotation.VisibleForTesting
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.resources.R

internal class ErrorDataTransformations : Transformations<FailureResult, ErrorData> {

    override fun transformTo(from: FailureResult): ErrorData {
        return when(from) {
            is FailureResult.NoDataFound -> noDataFoundErrorData
            is FailureResult.NoInternet -> noInternetErrorData
            is FailureResult.ClientError -> clientErrorData
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
                    clientErrorData
                }
            }
        }
    }
}

@VisibleForTesting
internal val noDataFoundErrorData: ErrorData
    get() = ErrorData.LocalErrorData(
        titleRes = R.string.title_no_data_found,
        messageRes = R.string.message_no_data_found,
        allowRetry = false
    )

@VisibleForTesting
internal val noInternetErrorData: ErrorData
    get() = ErrorData.LocalErrorData(
        titleRes = R.string.title_offline,
        messageRes = R.string.message_offline,
        allowRetry = true
    )

@VisibleForTesting
internal val clientErrorData: ErrorData
    get() = ErrorData.LocalErrorData(
        titleRes = R.string.title_error,
        messageRes = R.string.message_generic_error,
        allowRetry = true
    )