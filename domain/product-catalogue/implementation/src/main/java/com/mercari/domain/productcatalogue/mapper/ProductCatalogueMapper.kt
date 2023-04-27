package com.mercari.domain.productcatalogue.mapper

import androidx.annotation.VisibleForTesting
import com.logs.logger.Logger
import com.mercari.domain.shared.mapper.Mapper
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem

internal class ProductCatalogueMapperImpl :
    Mapper<ProductCatalogueItemResponse, ProductCatalogueItem> {

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

    @VisibleForTesting
    fun getFormattedPrice(price: Int): String {
        return "$price ¥"
    }

    @VisibleForTesting
    fun getStatus(status: String): ProductCatalogueItem.Status {
        return ProductCatalogueItem.Status.values().firstOrNull {
            it.value == status
        } ?: run {
            Logger.e(IllegalArgumentException("Unknown ProductCatalogueItem.status = $status"))
            ProductCatalogueItem.Status.UNKNOWN
        }
    }
}
