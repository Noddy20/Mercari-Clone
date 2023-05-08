package com.mercari.domain.productcatalogue.usecase

import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.domain.shared.mapper.Mapper
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.domain.shared.result.ResultData
import com.utils.multithreading.coroutines.DispatchersProvider
import javax.inject.Inject

internal class GetWomenProductCatalogueUseCaseImpl @Inject constructor(
    dispatchersProvider: DispatchersProvider,
    private val repository: ProductCatalogueRepository,
    mapper: Mapper<ProductCatalogueItemResponse, ProductCatalogueItem>,
    errorMapper: Mapper<ErrorResponse, FailureResult>
) : ProductCatalogueUseCaseImpl(dispatchersProvider, mapper, errorMapper) {

    override suspend fun invoke(): ResultData<List<ProductCatalogueItem>> {
        return invoke(repository::getWomenProductCatalogue)
    }
}
