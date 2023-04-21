package com.mercari.domain.productcatalogue.usecase

import com.mercari.domain.shared.usecase.SuspendableUseCase
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.result.ResultData

interface GetProductCatalogueUseCase : SuspendableUseCase<ResultData<List<ProductCatalogueItem>>>

const val QUALIFIER_GET_MAN_PRODUCT_CATALOGUE_USE_CASE = "GetManProductCatalogueUseCase"
const val QUALIFIER_GET_WOMEN_PRODUCT_CATALOGUE_USE_CASE = "GetWomenProductCatalogueUseCase"
const val QUALIFIER_GET_ALL_PRODUCT_CATALOGUE_USE_CASE = "GetAllProductCatalogueUseCase"