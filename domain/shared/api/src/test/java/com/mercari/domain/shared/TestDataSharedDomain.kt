package com.mercari.domain.shared

import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.domain.shared.error.FailureResult

val errorResponseNoDataFound = ErrorResponse.NoDataFound
val errorResponseNoInternet = ErrorResponse.NoInternet
val errorResponseServerError = ErrorResponse.ServerError()
val errorResponseServerErrorWithData = ErrorResponse.ServerError(
    errorData = ErrorResponse.ServerError.ErrorData(title = "test_title", message = "test_message")
)
val errorResponseClientError = ErrorResponse.ClientError

val failureResultNoDataFound = FailureResult.NoDataFound
val failureResultNoInternet = FailureResult.NoInternet
val failureResultServerError = FailureResult.ServerError()
val failureResultServerErrorWithData = FailureResult.ServerError(
    errorData = FailureResult.ServerError.ErrorData(title = "test_title", message = "test_message")
)
val failureResultClientError = FailureResult.ClientError