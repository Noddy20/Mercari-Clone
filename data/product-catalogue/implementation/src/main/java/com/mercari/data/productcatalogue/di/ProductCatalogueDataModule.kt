package com.mercari.data.productcatalogue.di

import com.mercari.data.productcatalogue.ProductCatalogueRepository
import com.mercari.data.productcatalogue.repository.ProductCatalogueRepositoryImpl
import com.mercari.data.productcatalogue.service.ProductCatalogueService
import com.netowrk.networkclient.clientfactory.NetworkClientFactory
import com.utils.di.providers.Provider
import com.utils.di.providers.lazyProvider
import com.utils.multithreading.coroutines.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object ProductCatalogueDataModule {

    @Provides
    @ActivityRetainedScoped
    fun providesProductCatalogueService(
        networkClientFactoryProvider: Provider<NetworkClientFactory>
    ): Provider<ProductCatalogueService> {
        return lazyProvider {
            networkClientFactoryProvider.get().create(ProductCatalogueService::class.java)
        }
    }

    @Provides
    @ActivityRetainedScoped
    fun providesProductCatalogueRepository(
        dispatchersProvider: DispatchersProvider,
        productCatalogueServiceProvider: Provider<ProductCatalogueService>
    ): ProductCatalogueRepository {
        return ProductCatalogueRepositoryImpl(
            dispatchersProvider = dispatchersProvider,
            productCatalogueServiceProvider = productCatalogueServiceProvider
        )
    }
}
