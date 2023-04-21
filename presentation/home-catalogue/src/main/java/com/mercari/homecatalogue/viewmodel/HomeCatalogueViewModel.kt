package com.mercari.homecatalogue.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktx.corektx.mutableStateFlow
import com.mercari.domain.productcatalogue.usecase.GetProductCatalogueUseCase
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_ALL_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_MAN_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_WOMEN_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.result.ResultData
import com.mercari.presentation.shared.transformations.ErrorDataTransformations
import com.utils.multithreading.coroutines.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import com.mercari.homecatalogue.viewmodel.HomeCatalogueContract.State
import com.mercari.homecatalogue.viewmodel.HomeCatalogueContract.Intent

@HiltViewModel
internal class HomeCatalogueViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    @Named(QUALIFIER_GET_MAN_PRODUCT_CATALOGUE_USE_CASE)
    private val getManProductCatalogueUseCase: GetProductCatalogueUseCase,
    @Named(QUALIFIER_GET_WOMEN_PRODUCT_CATALOGUE_USE_CASE)
    private val getWomenProductCatalogueUseCase: GetProductCatalogueUseCase,
    @Named(QUALIFIER_GET_ALL_PRODUCT_CATALOGUE_USE_CASE)
    private val getAllProductCatalogueUseCase: GetProductCatalogueUseCase,
    private val errorDataTransformations: ErrorDataTransformations
) : ViewModel(), HomeCatalogueContract {

    private val _state = mutableStateFlow<State>(State.CatalogueLoading)
    override val state: StateFlow<State>
        get() = _state

    override fun processIntent(intent: Intent) {
        viewModelScope.launch(dispatchersProvider.default) {
            val fetchBlock = when(intent) {
                Intent.LoadManCatalogue -> getManProductCatalogueUseCase::invoke
                Intent.LoadWomenCatalogue -> getWomenProductCatalogueUseCase::invoke
                Intent.LoadAllCatalogue -> getAllProductCatalogueUseCase::invoke
            }
            fetchProductCatalogue(fetchBlock)
        }
    }

    @VisibleForTesting
    suspend fun fetchProductCatalogue(block: suspend () -> ResultData<List<ProductCatalogueItem>>) {
        _state.tryEmit(State.CatalogueLoading)
        when(val result = block()) {
            is ResultData.Success -> {
                _state.tryEmit(State.CatalogueLoaded(result.data))
            }
            is ResultData.Failure -> {
                val errorData = errorDataTransformations.transformTo(result.failureResult)
                _state.tryEmit(State.CatalogueLoadError(errorData))
            }
        }
    }
}