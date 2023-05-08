package com.mercari.homecatalogue

import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.resources.R

const val testProductId = "test_id"
const val testProductName = "test_name"
const val testNumComments = 10
const val testNumLikes = 20
const val testPhoto = "test_photo"
const val testPrice = 50
const val testFormattedPrice = "50 ¥"

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

val failureResultNoDataFound = FailureResult.NoDataFound
val noDataFoundErrorData: ErrorData
    get() = ErrorData.LocalErrorData(
        titleRes = R.string.title_no_data_found,
        messageRes = R.string.message_no_data_found,
        allowRetry = false
    )