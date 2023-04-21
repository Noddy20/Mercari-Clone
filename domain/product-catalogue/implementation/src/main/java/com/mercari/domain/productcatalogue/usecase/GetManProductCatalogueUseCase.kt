package com.mercari.domain.productcatalogue.usecase

import com.logs.logger.Logger
import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.domain.productcatalogue.mapper.ProductCatalogueMapper
import com.mercari.domain.shared.mapper.ResponseErrorMapper
import com.mercari.model.data.shared.result.ResponseResult
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.domain.shared.result.ResultData
import com.utils.multithreading.coroutines.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetManProductCatalogueUseCaseImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val repository: ProductCatalogueRepository,
    private val mapper: ProductCatalogueMapper,
    private val errorMapper: ResponseErrorMapper
) : GetProductCatalogueUseCase {

    override suspend fun invoke(): ResultData<List<ProductCatalogueItem>> = withContext(
        dispatchersProvider.default
    ) {
        return@withContext when(val response = repository.getManProductCatalogue()) {
            is ResponseResult.Success -> {
                try {
                    val resultData = response.data.map { mapper.mapTo(it) }
                    ResultData.Success(resultData)
                } catch (e: Exception) {
                    Logger.e(e)
                    ResultData.Failure(FailureResult.ClientError)
                }
            }
            is ResponseResult.Failure -> {
                ResultData.Failure(errorMapper.mapTo(response.errorResponse))
            }
        }
    }
}