package com.mercari.domain.productcatalogue

import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult

const val testProductId = "test_id"
const val testProductName = "test_name"
const val testNumComments = 10
const val testNumLikes = 20
const val testPhoto = "test_photo"
const val testPrice = 50
const val testFormattedPrice = "50 ¥"
const val testStatusSold = "sold_out"
const val testStatusOnSale = "on_sale"

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

val productCatalogueItem: ProductCatalogueItem
    get() = ProductCatalogueItem(
        id = testProductId,
        name = testProductName,
        numberOfComments = testNumComments,
        numberOfLikes = testNumLikes,
        photoUrl = testPhoto,
        price = testPrice,
        priceFormatted = testFormattedPrice,
        status = ProductCatalogueItem.Status.SOLD_OUT
    )

val productCatalogueItemsList: List<ProductCatalogueItem>
    get() = listOf(
        productCatalogueItem,
        productCatalogueItem,
        productCatalogueItem,
        productCatalogueItem
    )

val errorResponseNoDataFound = ErrorResponse.NoDataFound
val errorResponseNoInternet = ErrorResponse.NoInternet
val errorResponseServerError = ErrorResponse.ServerError()
val errorResponseClientError = ErrorResponse.ClientError

val failureResultNoDataFound = FailureResult.NoDataFound
val failureResultNoInternet = FailureResult.NoInternet
val failureResultServerError = FailureResult.ServerError()
val failureResultClientError = FailureResult.ClientError
