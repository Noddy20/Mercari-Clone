package com.mercari.data.productcatalogue.repository

import androidx.annotation.VisibleForTesting
import com.logs.logger.Logger
import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.data.productcatalogue.service.ProductCatalogueService
import com.mercari.model.data.productcatalogue.response.ProductCatalogueItemResponse
import com.mercari.model.data.shared.error.ErrorResponse
import com.mercari.model.data.shared.result.ResponseResult
import com.utils.di.providers.Provider
import com.utils.multithreading.coroutines.DispatchersProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

internal class ProductCatalogueRepositoryImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val productCatalogueServiceProvider: Provider<ProductCatalogueService>
) : ProductCatalogueRepository {

    @VisibleForTesting
    val productCatalogueService: ProductCatalogueService
        get() = productCatalogueServiceProvider.get()

    override suspend fun getManProductCatalogue()
            : ResponseResult<List<ProductCatalogueItemResponse>> = getProductCatalogue(
        productCatalogueService::getManCatalogue
    )

    override suspend fun getWomenProductCatalogue()
            : ResponseResult<List<ProductCatalogueItemResponse>> = getProductCatalogue(
        productCatalogueService::getWomenCatalogue
    )

    override suspend fun getAllProductCatalogue()
    : ResponseResult<List<ProductCatalogueItemResponse>> = withContext(dispatchersProvider.io) {
        runCatching {
            val manCatalogueResponseTask = async {
                productCatalogueService.getManCatalogue()
            }
            val womenCatalogueResponseTask = async {
                productCatalogueService.getWomenCatalogue()
            }
            val manCatalogueResponse = manCatalogueResponseTask.await()
            val womenCatalogueResponse = womenCatalogueResponseTask.await()

            val allCatalogue = arrayListOf<ProductCatalogueItemResponse>()
            allCatalogue.addAll(manCatalogueResponse.orEmpty())
            allCatalogue.addAll(womenCatalogueResponse.orEmpty())

            return@withContext when {
                manCatalogueResponse == null && womenCatalogueResponse == null -> {
                    ResponseResult.Failure(ErrorResponse.ServerError())
                }
                allCatalogue.isEmpty() -> {
                    ResponseResult.Failure(ErrorResponse.NoDataFound)
                }
                else -> {
                    ResponseResult.Success(allCatalogue)
                }
            }
        }.onFailure {
            Logger.e(it)
            if (it is UnknownHostException) {
                return@withContext ResponseResult.Failure(ErrorResponse.NoInternet)
            }
        }
        return@withContext ResponseResult.Failure(ErrorResponse.ClientError)
    }

    private suspend fun getProductCatalogue(
        block: suspend () -> List<ProductCatalogueItemResponse>?
    ) = withContext(dispatchersProvider.io) {
        runCatching {
            val response = block()
            return@withContext when {
                response == null -> ResponseResult.Failure(ErrorResponse.ServerError())
                response.isEmpty() -> ResponseResult.Failure(ErrorResponse.NoDataFound)
                else -> ResponseResult.Success(response)
            }
        }.onFailure {
            Logger.e(it)
            if (it is UnknownHostException) {
                return@withContext ResponseResult.Failure(ErrorResponse.NoInternet)
            }
        }
        return@withContext ResponseResult.Failure(ErrorResponse.ClientError)
    }
}