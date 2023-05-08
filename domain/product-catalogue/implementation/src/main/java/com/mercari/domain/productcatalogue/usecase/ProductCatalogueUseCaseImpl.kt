package com.mercari.domain.productcatalogue.usecase

import com.logs.logger.Logger
import com.mercari.domain.shared.mapper.Mapper
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.data.shared.result.ResponseResult
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.domain.shared.result.ResultData
import com.utils.multithreading.coroutines.DispatchersProvider
import kotlinx.coroutines.withContext

internal abstract class ProductCatalogueUseCaseImpl(
    private val dispatchersProvider: DispatchersProvider,
    private val mapper: Mapper<ProductCatalogueItemResponse, ProductCatalogueItem>,
    private val errorMapper: Mapper<ErrorResponse, FailureResult>
) : GetProductCatalogueUseCase {

    suspend fun invoke(
        block: suspend () -> ResponseResult<List<ProductCatalogueItemResponse>>
    ): ResultData<List<ProductCatalogueItem>> = withContext(dispatchersProvider.default) {
        return@withContext when (val response = block()) {
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
