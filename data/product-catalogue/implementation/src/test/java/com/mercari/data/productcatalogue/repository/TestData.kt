package com.mercari.data.productcatalogue.repository

import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse

const val testProductId = "test_id"
const val testProductName = "test_name"
const val testNumComments = 10
const val testNumLikes = 20
const val testPhoto = "test_photo"
const val testPrice = 50
const val testStatusSold = "sold_out"
const val testStatusOnSale = "on_sale"
const val testStatusOnUnknown = "unknown"

val productCatalogueResponse: ProductCatalogueItemResponse
    get() = ProductCatalogueItemResponse(
        id = testProductId,
        name = testProductName,
        numComments = testNumComments,
        numLikes = testNumLikes,
        photo = testPhoto,
        price = testPrice,
        status = testStatusSold
    )

val productCatalogueResponseList: List<ProductCatalogueItemResponse>
    get() = listOf(
        productCatalogueResponse,
        productCatalogueResponse,
        productCatalogueResponse,
        productCatalogueResponse
    )