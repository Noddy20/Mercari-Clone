package com.utils.multithreading.di

import com.utils.multithreading.coroutines.DispatchersProvider
import com.utils.multithreading.coroutines.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

    @Provides
    @Singleton
    fun providesDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl()
}
