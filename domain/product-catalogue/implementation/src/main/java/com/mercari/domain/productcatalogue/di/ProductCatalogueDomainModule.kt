package com.mercari.domain.productcatalogue.di

import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.domain.productcatalogue.mapper.ProductCatalogueMapper
import com.mercari.domain.productcatalogue.usecase.GetAllProductCatalogueUseCaseImpl
import com.mercari.domain.productcatalogue.usecase.GetManProductCatalogueUseCaseImpl
import com.mercari.domain.productcatalogue.usecase.GetProductCatalogueUseCase
import com.mercari.domain.productcatalogue.usecase.GetWomenProductCatalogueUseCaseImpl
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_ALL_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_MAN_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.domain.productcatalogue.usecase.QUALIFIER_GET_WOMEN_PRODUCT_CATALOGUE_USE_CASE
import com.mercari.domain.shared.mapper.ResponseErrorMapper
import com.utils.multithreading.coroutines.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object ProductCatalogueDomainModule {

    @Provides
    @ActivityRetainedScoped
    @Named(QUALIFIER_GET_MAN_PRODUCT_CATALOGUE_USE_CASE)
    fun providesGetManProductCatalogueUseCase(
        dispatchersProvider: DispatchersProvider,
        productCatalogueRepository: ProductCatalogueRepository
    ): GetProductCatalogueUseCase {
        return GetManProductCatalogueUseCaseImpl(
            dispatchersProvider = dispatchersProvider,
            repository = productCatalogueRepository,
            mapper = ProductCatalogueMapper(),
            errorMapper = ResponseErrorMapper()
        )
    }

    @Provides
    @ActivityRetainedScoped
    @Named(QUALIFIER_GET_WOMEN_PRODUCT_CATALOGUE_USE_CASE)
    fun providesGetWomenProductCatalogueUseCase(
        dispatchersProvider: DispatchersProvider,
        productCatalogueRepository: ProductCatalogueRepository
    ): GetProductCatalogueUseCase {
        return GetWomenProductCatalogueUseCaseImpl(
            dispatchersProvider = dispatchersProvider,
            repository = productCatalogueRepository,
            mapper = ProductCatalogueMapper(),
            errorMapper = ResponseErrorMapper()
        )
    }

    @Provides
    @ActivityRetainedScoped
    @Named(QUALIFIER_GET_ALL_PRODUCT_CATALOGUE_USE_CASE)
    fun providesGetAllProductCatalogueUseCase(
        dispatchersProvider: DispatchersProvider,
        productCatalogueRepository: ProductCatalogueRepository
    ): GetProductCatalogueUseCase {
        return GetAllProductCatalogueUseCaseImpl(
            dispatchersProvider = dispatchersProvider,
            repository = productCatalogueRepository,
            mapper = ProductCatalogueMapper(),
            errorMapper = ResponseErrorMapper()
        )
    }
}