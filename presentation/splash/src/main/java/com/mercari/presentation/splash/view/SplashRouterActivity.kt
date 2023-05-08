package com.mercari.presentation.splash.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mercari.presentation.navigation.navigators.AppActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
internal class SplashRouterActivity : ComponentActivity() {

    @Inject
    lateinit var appActivityNavigator: AppActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY_IN_SECONDS.seconds)
            appActivityNavigator.launchHomeContainerActivity()
            finish()
        }
    }
}

private const val SPLASH_SCREEN_DELAY_IN_SECONDS: Long = 2
