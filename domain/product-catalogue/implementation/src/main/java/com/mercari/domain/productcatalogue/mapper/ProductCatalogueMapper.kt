package com.mercari.domain.productcatalogue.mapper

import com.logs.logger.Logger
import com.mercari.domain.shared.mapper.Mapper
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem

internal interface ProductCatalogueMapper
    : Mapper<ProductCatalogueItemResponse, ProductCatalogueItem> {
        fun getFormattedPrice(price: Int): String
        fun getStatus(status: String): ProductCatalogueItem.Status
}

internal class ProductCatalogueMapperImpl : ProductCatalogueMapper {

    override fun mapTo(from: ProductCatalogueItemResponse): ProductCatalogueItem {
        return ProductCatalogueItem(
            id = from.id,
            name = from.name,
            numberOfComments = from.numComments,
            numberOfLikes = from.numLikes,
            photoUrl = from.photo,
            price = from.price,
            priceFormatted = getFormattedPrice(from.price),
            status = getStatus(from.status)
        )
    }

    override fun getFormattedPrice(price: Int): String {
        return "$price ¥"
    }

    override fun getStatus(status: String): ProductCatalogueItem.Status {
        return ProductCatalogueItem.Status.values().firstOrNull {
            it.value == status
        } ?: run {
            Logger.e(IllegalArgumentException("Unknown ProductCatalogueItem.status = $status"))
            ProductCatalogueItem.Status.UNKNOWN
        }
    }
}
