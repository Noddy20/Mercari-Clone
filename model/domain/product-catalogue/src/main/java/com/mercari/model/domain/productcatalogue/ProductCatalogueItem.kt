package com.mercari.model.domain.productcatalogue

data class ProductCatalogueItem(
    val id: String,
    val name: String,
    val numberOfComments: Int = 0,
    val numberOfLikes: Int = 0,
    val photoUrl: String,
    val price: Int,
    val priceFormatted: String,
    val status: Status
) {
    enum class Status(val value: String) {
        SOLD_OUT("sold_out"), ON_SALE("on_sale"), UNKNOWN("unknown")
    }
}