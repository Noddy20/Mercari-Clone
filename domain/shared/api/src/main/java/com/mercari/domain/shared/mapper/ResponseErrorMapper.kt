package com.mercari.domain.shared.mapper

import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.domain.shared.error.FailureResult

interface ResponseErrorMapper : Mapper<ErrorResponse, FailureResult>

class ResponseErrorMapperImpl : ResponseErrorMapper {

    override fun mapTo(from: ErrorResponse): FailureResult {
        return when(from) {
            is ErrorResponse.NoDataFound -> FailureResult.NoDataFound
            is ErrorResponse.ClientError -> FailureResult.ClientError
            is ErrorResponse.NoInternet -> FailureResult.NoInternet
            is ErrorResponse.ServerError -> {
                FailureResult.ServerError(
                    if (from.errorData == null) {
                        null
                    } else {
                        FailureResult.ServerError.ErrorData(
                            title = from.errorData?.title.orEmpty(),
                            message = from.errorData?.message.orEmpty()
                        )
                    }
                )
            }
        }
    }
}