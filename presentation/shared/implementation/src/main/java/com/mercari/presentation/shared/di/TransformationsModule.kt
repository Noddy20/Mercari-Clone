package com.mercari.presentation.shared.di

import com.mercari.presentation.shared.transformations.ErrorDataTransformations
import com.mercari.presentation.shared.transformations.ErrorDataTransformationsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object TransformationsModule {

    @Provides
    @ActivityRetainedScoped
    fun providesErrorDataTransformations(): ErrorDataTransformations {
        return ErrorDataTransformationsImpl()
    }
}