package com.mercari.presentation.shared.transformations

import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.presentation.shared.ErrorData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorDataTransformationsTest {

    private lateinit var transformations: Transformations<FailureResult, ErrorData>

    @Before
    fun setup() {
        transformations = ErrorDataTransformations()
    }

    @Test
    fun `should return respective local error data when error is no data found`() {
        val failureResult = FailureResult.NoDataFound
        val expectedErrorData = noDataFoundErrorData

        val errorData = transformations.transformTo(failureResult)

        assertEquals(expectedErrorData, errorData)
    }

    @Test
    fun `should return respective local error data when error is no internet`() {
        val failureResult = FailureResult.NoInternet
        val expectedErrorData = noInternetErrorData

        val errorData = transformations.transformTo(failureResult)

        assertEquals(expectedErrorData, errorData)
    }

    @Test
    fun `should return respective local error data when error is client error`() {
        val failureResult = FailureResult.ClientError
        val expectedErrorData = clientErrorData

        val errorData = transformations.transformTo(failureResult)

        assertEquals(expectedErrorData, errorData)
    }

    @Test
    fun `should return client local error data when server error has no error data`() {
        val failureResult = FailureResult.ServerError(errorData = null)
        val expectedErrorData = clientErrorData

        val errorData = transformations.transformTo(failureResult)

        assertEquals(expectedErrorData, errorData)
    }

    @Test
    fun `should return remoter error data when server error has error data`() {
        val errorTitle = "error_title"
        val errorMessage = "error_message"
        val failureResult = FailureResult.ServerError(
            errorData = FailureResult.ServerError.ErrorData(
                title = errorTitle,
                message = errorMessage
            )
        )
        val expectedErrorData = ErrorData.RemoteErrorData(
            title = errorTitle,
            message = errorMessage
        )

        val errorData = transformations.transformTo(failureResult)

        assertEquals(expectedErrorData, errorData)
    }
}