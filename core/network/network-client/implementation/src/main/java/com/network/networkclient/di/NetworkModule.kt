package com.network.networkclient.di

import com.logs.logger.Logger
import com.netowrk.networkclient.clientfactory.NetworkClientFactory
import com.network.networkclient.BuildConfig
import com.network.networkclient.clientfactory.NetworkClientFactoryImpl
import com.utils.di.providers.Provider
import com.utils.di.providers.asyncSingletonProvider
import com.utils.multithreading.coroutines.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkClientFactoryProvider(
        dispatchersProvider: DispatchersProvider
    ): Provider<NetworkClientFactory> {
        Logger.d("AsyncProvider providesNetworkClientFactoryProvider")
        return asyncSingletonProvider("NetworkClientFactoryImpl", dispatchersProvider.default) {
            NetworkClientFactoryImpl(baseUrl = BuildConfig.BASE_URL)
        }
    }
}
