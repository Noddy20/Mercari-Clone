package com.mercari.domain.shared.mapper

import com.mercari.domain.shared.errorResponseClientError
import com.mercari.domain.shared.errorResponseNoDataFound
import com.mercari.domain.shared.errorResponseNoInternet
import com.mercari.domain.shared.errorResponseServerError
import com.mercari.domain.shared.errorResponseServerErrorWithData
import com.mercari.domain.shared.failureResultClientError
import com.mercari.domain.shared.failureResultNoDataFound
import com.mercari.domain.shared.failureResultNoInternet
import com.mercari.domain.shared.failureResultServerError
import com.mercari.domain.shared.failureResultServerErrorWithData
import com.mercari.model.domain.shared.error.FailureResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResponseErrorMapperTest {

    private lateinit var mapper: ResponseErrorMapperImpl

    @Before
    fun setup() {
        mapper = ResponseErrorMapperImpl()
    }

    @Test
    fun `should map no data found error response to no data found failure result`() {
        val errorResponse = errorResponseNoDataFound
        val expectedFailureResult = failureResultNoDataFound

        val result = mapper.mapTo(errorResponse)

        assertEquals(expectedFailureResult, result)
    }

    @Test
    fun `should map no internet error response to no internet failure result`() {
        val errorResponse = errorResponseNoInternet
        val expectedFailureResult = failureResultNoInternet

        val result = mapper.mapTo(errorResponse)

        assertEquals(expectedFailureResult, result)
    }

    @Test
    fun `should map server error response to server error failure without error data result`() {
        val errorResponse = errorResponseServerError
        val expectedFailureResult = failureResultServerError

        val result = mapper.mapTo(errorResponse)

        assert(result is FailureResult.ServerError)
        assertEquals(expectedFailureResult.errorData, (result as FailureResult.ServerError).errorData)
    }

    @Test
    fun `should map server error response to server error failure with error data result`() {
        val errorResponse = errorResponseServerErrorWithData
        val expectedFailureResult = failureResultServerErrorWithData

        val result = mapper.mapTo(errorResponse)

        assert(result is FailureResult.ServerError)
        assertEquals(expectedFailureResult.errorData, (result as FailureResult.ServerError).errorData)
    }

    @Test
    fun `should map client error response to client error failure result`() {
        val errorResponse = errorResponseClientError
        val expectedFailureResult = failureResultClientError

        val result = mapper.mapTo(errorResponse)

        assertEquals(expectedFailureResult, result)
    }
}