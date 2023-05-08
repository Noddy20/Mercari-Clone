package com.mercari.data.productcatalogue.repository

import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.data.productcatalogue.service.ProductCatalogueService
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.data.shared.result.ResponseResult
import com.utils.di.providers.Provider
import com.utils.testutils.coroutines.base.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class ProductCatalogueRepositoryImplTest : BaseUnitTest() {

    private lateinit var repository: ProductCatalogueRepository
    private lateinit var service: ProductCatalogueService
    private lateinit var serviceProvider: Provider<ProductCatalogueService>

    @Before
    override fun setup() {
        super.setup()
        service = mock()
        serviceProvider = mock()
        repository = ProductCatalogueRepositoryImpl(
            dispatchersProvider = dispatchersProvider,
            productCatalogueServiceProvider = serviceProvider
        )

        whenever(serviceProvider.get()).thenReturn(service)
    }

    /**
     *   Man Catalogue Fetch Tests
     */

    @Test
    fun `should return success result when man catalogue fetched successfully`() = runTest {
        val expectedResponse = productCatalogueResponseList
        whenever(service.getManCatalogue())
            .thenReturn(expectedResponse)

        val response = repository.getManProductCatalogue()

        verify(service).getManCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(expectedResponse, (response as ResponseResult.Success).data)
    }

    @Test
    fun `should return server error response when man catalogue response is null`() = runTest {
        whenever(service.getManCatalogue())
            .thenReturn(null)

        val response = repository.getManProductCatalogue()

        verify(service).getManCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.ServerError)
    }

    @Test
    fun `should return no data found response when man catalogue response is empty`() = runTest {
        whenever(service.getManCatalogue())
            .thenReturn(emptyList())

        val response = repository.getManProductCatalogue()

        verify(service).getManCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoDataFound)
    }

    @Test
    fun `should return no internet response when man catalogue fetch throws UnknownHostException`() = runTest {
        doAnswer {
            throw UnknownHostException("No Internet!")
        }.whenever(service).getManCatalogue()

        val response = repository.getManProductCatalogue()

        verify(service).getManCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoInternet)
    }

    @Test
    fun `should return client error response when man catalogue fetch throws exception other than UnknownHostException`() = runTest {
        whenever(service.getManCatalogue())
            .thenThrow(IllegalArgumentException("Parsing Failed!"))

        val response = repository.getManProductCatalogue()

        verify(service).getManCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.ClientError)
    }

    /**
     *   Women Catalogue Fetch Tests
     */

    @Test
    fun `should return success result when women catalogue fetched successfully`() = runTest {
        val expectedResponse = productCatalogueResponseList
        whenever(service.getWomenCatalogue())
            .thenReturn(expectedResponse)

        val response = repository.getWomenProductCatalogue()

        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(expectedResponse, (response as ResponseResult.Success).data)
    }

    @Test
    fun `should return server error response when women catalogue response is null`() = runTest {
        whenever(service.getWomenCatalogue())
            .thenReturn(null)

        val response = repository.getWomenProductCatalogue()

        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.ServerError)
    }

    @Test
    fun `should return no data found response when women catalogue response is empty`() = runTest {
        whenever(service.getWomenCatalogue())
            .thenReturn(emptyList())

        val response = repository.getWomenProductCatalogue()

        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoDataFound)
    }

    @Test
    fun `should return no internet response when women catalogue fetch throws UnknownHostException`() = runTest {
        doAnswer {
            throw UnknownHostException("No Internet!")
        }.whenever(service).getWomenCatalogue()

        val response = repository.getWomenProductCatalogue()

        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoInternet)
    }

    @Test
    fun `should return client error response when women catalogue fetch throws exception other than UnknownHostException`() = runTest {
        whenever(service.getWomenCatalogue())
            .thenThrow(IllegalArgumentException("Parsing Failed!"))

        val response = repository.getWomenProductCatalogue()

        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.ClientError)
    }

    /**
     *   All Catalogue Fetch Tests
     */

    @Test
    fun `should return success result when man & women catalogue fetched successfully`() = runTest {
        val testResponse = productCatalogueResponseList
        val expectedResponse = testResponse + testResponse
        whenever(service.getManCatalogue())
            .thenReturn(testResponse)
        whenever(service.getWomenCatalogue())
            .thenReturn(testResponse)

        val response = repository.getAllProductCatalogue()
        println("Response_here $response")

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(expectedResponse, (response as ResponseResult.Success).data)
    }

    @Test
    fun `should return success result when man catalogue fetched successfully but women catalogue fetch failed`() = runTest {
        val testResponse = productCatalogueResponseList
        whenever(service.getManCatalogue())
            .thenReturn(testResponse)
        whenever(service.getWomenCatalogue())
            .thenReturn(null)

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(testResponse, (response as ResponseResult.Success).data)
    }

    @Test
    fun `should return success result when women catalogue fetched successfully but man catalogue fetch failed`() = runTest {
        val testResponse = productCatalogueResponseList
        whenever(service.getManCatalogue())
            .thenReturn(null)
        whenever(service.getWomenCatalogue())
            .thenReturn(testResponse)

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(testResponse, (response as ResponseResult.Success).data)
    }

    @Test
    fun `should return server error response when man & women catalogue response are null`() = runTest {
        whenever(service.getManCatalogue())
            .thenReturn(null)
        whenever(service.getWomenCatalogue())
            .thenReturn(null)

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.ServerError)
    }

    @Test
    fun `should return no data found response when man & women catalogue response are empty`() = runTest {
        whenever(service.getManCatalogue())
            .thenReturn(emptyList())
        whenever(service.getWomenCatalogue())
            .thenReturn(emptyList())

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoDataFound)
    }

    @Test
    fun `should return no internet response when any catalogue fetch throws UnknownHostException`() = runTest {
        doAnswer {
            throw UnknownHostException("No Internet!")
        }.whenever(service).getManCatalogue()

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Failure)
        assert((response as ResponseResult.Failure).errorResponse is ErrorResponse.NoInternet)
    }

    @Test
    fun `should return success response when any one catalogue fetch throws exception other than UnknownHostException`() = runTest {
        val expectedResponse = productCatalogueResponseList
        whenever(service.getManCatalogue())
            .thenReturn(expectedResponse)
        whenever(service.getWomenCatalogue())
            .thenThrow(IllegalArgumentException("Parsing Failed!"))

        val response = repository.getAllProductCatalogue()

        verify(service).getManCatalogue()
        verify(service).getWomenCatalogue()
        assert(response is ResponseResult.Success)
        assertEquals(expectedResponse, (response as ResponseResult.Success).data)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(service)
    }
}
