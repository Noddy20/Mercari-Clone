package com.mercari.domain.productcatalogue.usecase

import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.domain.productcatalogue.errorResponseClientError
import com.mercari.domain.productcatalogue.errorResponseNoDataFound
import com.mercari.domain.productcatalogue.errorResponseNoInternet
import com.mercari.domain.productcatalogue.errorResponseServerError
import com.mercari.domain.productcatalogue.failureResultClientError
import com.mercari.domain.productcatalogue.failureResultNoDataFound
import com.mercari.domain.productcatalogue.failureResultNoInternet
import com.mercari.domain.productcatalogue.failureResultServerError
import com.mercari.domain.productcatalogue.productCatalogueItem
import com.mercari.domain.productcatalogue.productCatalogueItemsList
import com.mercari.domain.productcatalogue.productCatalogueResponseList
import com.mercari.domain.shared.mapper.Mapper
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.data.shared.result.ResponseResult
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.domain.shared.result.ResultData
import com.utils.testutils.coroutines.base.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllProductCatalogueUseCaseImplTest : BaseUnitTest() {

    private lateinit var repository: ProductCatalogueRepository
    private lateinit var mapper: Mapper<ProductCatalogueItemResponse, ProductCatalogueItem>
    private lateinit var errorMapper: Mapper<ErrorResponse, FailureResult>

    private lateinit var useCase: GetAllProductCatalogueUseCaseImpl

    @Before
    override fun setup() {
        super.setup()
        repository = mock()
        mapper = mock()
        errorMapper = mock()

        useCase = GetAllProductCatalogueUseCaseImpl(
            dispatchersProvider = dispatchersProvider,
            repository = repository,
            mapper = mapper,
            errorMapper = errorMapper
        )
    }

    @Test
    fun `should return success result when all product catalogue response is success`() = runTest {
        val productCatalogueResponse = ResponseResult.Success(data = productCatalogueResponseList)
        val productCatalogueItem = productCatalogueItem
        val expectedResult = ResultData.Success(data = productCatalogueItemsList)

        whenever(repository.getAllProductCatalogue())
            .thenReturn(productCatalogueResponse)
        whenever(mapper.mapTo(any()))
            .thenReturn(productCatalogueItem)

        val result = useCase.invoke()

        verify(repository).getAllProductCatalogue()
        verify(mapper, times(productCatalogueResponse.data.size)).mapTo(any())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return no data found result when all product catalogue response is no data found`() = runTest {
        val productCatalogueResponse = ResponseResult.Failure<List<ProductCatalogueItemResponse>>(
            errorResponse = errorResponseNoDataFound
        )
        val expectedResult = ResultData.Failure<List<ProductCatalogueItem>>(
            failureResult = failureResultNoDataFound
        )

        whenever(repository.getAllProductCatalogue())
            .thenReturn(productCatalogueResponse)
        whenever(errorMapper.mapTo(any()))
            .thenReturn(failureResultNoDataFound)

        val result = useCase.invoke()

        verify(repository).getAllProductCatalogue()
        verify(errorMapper).mapTo(any())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return no internet result when all product catalogue response is no internet`() = runTest {
        val productCatalogueResponse = ResponseResult.Failure<List<ProductCatalogueItemResponse>>(
            errorResponse = errorResponseNoInternet
        )
        val expectedResult = ResultData.Failure<List<ProductCatalogueItem>>(
            failureResult = failureResultNoInternet
        )

        whenever(repository.getAllProductCatalogue())
            .thenReturn(productCatalogueResponse)
        whenever(errorMapper.mapTo(any()))
            .thenReturn(failureResultNoInternet)

        val result = useCase.invoke()

        verify(repository).getAllProductCatalogue()
        verify(errorMapper).mapTo(any())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return server error result when all product catalogue response is server error`() = runTest {
        val productCatalogueResponse = ResponseResult.Failure<List<ProductCatalogueItemResponse>>(
            errorResponse = errorResponseServerError
        )
        val expectedResult = ResultData.Failure<List<ProductCatalogueItem>>(
            failureResult = failureResultServerError
        )

        whenever(repository.getAllProductCatalogue())
            .thenReturn(productCatalogueResponse)
        whenever(errorMapper.mapTo(any()))
            .thenReturn(failureResultServerError)

        val result = useCase.invoke()

        verify(repository).getAllProductCatalogue()
        verify(errorMapper).mapTo(any())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return client error result when all product catalogue response is client error`() = runTest {
        val productCatalogueResponse = ResponseResult.Failure<List<ProductCatalogueItemResponse>>(
            errorResponse = errorResponseClientError
        )
        val expectedResult = ResultData.Failure<List<ProductCatalogueItem>>(
            failureResult = failureResultClientError
        )

        whenever(repository.getAllProductCatalogue())
            .thenReturn(productCatalogueResponse)
        whenever(errorMapper.mapTo(any()))
            .thenReturn(failureResultClientError)

        val result = useCase.invoke()

        verify(repository).getAllProductCatalogue()
        verify(errorMapper).mapTo(any())
        assertEquals(expectedResult, result)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository, mapper, errorMapper)
    }
}
