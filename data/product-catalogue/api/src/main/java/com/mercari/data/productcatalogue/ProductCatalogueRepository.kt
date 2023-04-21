package com.mercari.data.productcatalogue

import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.result.ResponseResult

interface ProductCatalogueRepository {
    suspend fun getManProductCatalogue(): ResponseResult<List<ProductCatalogueItemResponse>>
    suspend fun getWomenProductCatalogue(): ResponseResult<List<ProductCatalogueItemResponse>>
    suspend fun getAllProductCatalogue(): ResponseResult<List<ProductCatalogueItemResponse>>
}