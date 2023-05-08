package com.mercari.presentation.navigation.di

import android.app.Activity
import com.mercari.presentation.navigation.navigators.AppActivityNavigator
import com.mercari.presentation.navigation.navigators.AppActivityNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal object AppActivityNavigatorModule {

    @Provides
    @ActivityScoped
    fun providesAppActivityNavigator(
        activity: Activity
    ): AppActivityNavigator {
        return AppActivityNavigatorImpl(context = activity)
    }
}
