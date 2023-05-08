package com.mercari.domain.productcatalogue.mapper

import com.mercari.domain.productcatalogue.productCatalogueItem
import com.mercari.domain.productcatalogue.productCatalogueResponse
import com.mercari.domain.productcatalogue.testFormattedPrice
import com.mercari.domain.productcatalogue.testPrice
import com.mercari.domain.productcatalogue.testStatusOnSale
import com.mercari.domain.productcatalogue.testStatusSold
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever

class ProductCatalogueMapperTest {

    private lateinit var mapper: ProductCatalogueMapper

    @Before
    fun setup() {
        mapper = ProductCatalogueMapper()
    }

    @Test
    fun `should return formatted price in yen`() {
        val price = testPrice
        val expectedFormattedPrice = testFormattedPrice

        val result = mapper.getFormattedPrice(price)

        assertEquals(expectedFormattedPrice, result)
    }

    @Test
    fun `should return Sold Out status for sold out item`() {
        val status = testStatusSold
        val expectedStatus = ProductCatalogueItem.Status.SOLD_OUT

        val result = mapper.getStatus(status)

        assertEquals(expectedStatus, result)
    }

    @Test
    fun `should return On Sale status for on sale item`() {
        val status = testStatusOnSale
        val expectedStatus = ProductCatalogueItem.Status.ON_SALE

        val result = mapper.getStatus(status)

        assertEquals(expectedStatus, result)
    }

    @Test
    fun `should return Unknown status when status is invalid`() {
        val status = "invalid_unknown_status"
        val expectedStatus = ProductCatalogueItem.Status.UNKNOWN

        val result = mapper.getStatus(status)

        assertEquals(expectedStatus, result)
    }

    @Test
    fun `should map product catalogue data response to product catalogue domain model`() {
        val productCatalogueResponse = productCatalogueResponse
        val expectedProductCatalogueItem = productCatalogueItem

        val spyMapper = spy(mapper)
        whenever(spyMapper.getFormattedPrice(productCatalogueResponse.price))
            .thenReturn(expectedProductCatalogueItem.priceFormatted)
        whenever(spyMapper.getStatus(productCatalogueResponse.status))
            .thenReturn(expectedProductCatalogueItem.status)

        val result = spyMapper.mapTo(productCatalogueResponse)

        assertEquals(expectedProductCatalogueItem, result)
    }
}
