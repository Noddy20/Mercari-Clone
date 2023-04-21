package com.mercari.homecatalogue.viewmodel

import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.presentation.shared.viewmodel.ViewModelContract

interface HomeCatalogueContract :
    ViewModelContract<HomeCatalogueContract.Intent, HomeCatalogueContract.State> {

    sealed interface Intent {
        object LoadManCatalogue : Intent
        object LoadWomenCatalogue : Intent
        object LoadAllCatalogue : Intent
    }

    sealed interface State {
        object CatalogueLoading : State
        data class CatalogueLoaded(val catalogue: List<ProductCatalogueItem>) : State
        data class CatalogueLoadError(val errorData: ErrorData) : State
    }
}