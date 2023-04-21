package com.mercari.homecontainer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.homecontainer.view.composable.HomeContainerComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeContainerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercariTheme {
                HomeContainerComposable()
            }
        }
    }
}

fun getHomeContainerActivityIntent(
    context: Context
): Intent = Intent(context, HomeContainerActivity::class.java)
