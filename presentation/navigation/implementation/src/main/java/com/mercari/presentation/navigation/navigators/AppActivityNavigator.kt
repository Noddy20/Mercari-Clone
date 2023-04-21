package com.mercari.presentation.navigation.navigators

import android.content.Context
import com.mercari.homecontainer.view.getHomeContainerActivityIntent
import javax.inject.Inject

internal class AppActivityNavigatorImpl @Inject constructor(
    private val context: Context
) : AppActivityNavigator {

    override fun launchHomeContainerActivity() {
        context.startActivity(getHomeContainerActivityIntent(context))
    }
}