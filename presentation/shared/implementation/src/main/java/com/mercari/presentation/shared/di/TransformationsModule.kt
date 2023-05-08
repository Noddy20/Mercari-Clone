package com.mercari.presentation.shared.di

import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.presentation.shared.transformations.ErrorDataTransformations
import com.mercari.presentation.shared.transformations.Transformations
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
    fun providesErrorDataTransformations(): Transformations<FailureResult, ErrorData> {
        return ErrorDataTransformations()
    }
}
