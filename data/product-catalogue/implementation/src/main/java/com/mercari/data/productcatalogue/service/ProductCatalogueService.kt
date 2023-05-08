package com.mercari.data.productcatalogue.service

import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import retrofit2.http.GET

interface ProductCatalogueService {

    @GET("men.json")
    suspend fun getManCatalogue(): List<ProductCatalogueItemResponse>?

    @GET("women.json")
    suspend fun getWomenCatalogue(): List<ProductCatalogueItemResponse>?
}
