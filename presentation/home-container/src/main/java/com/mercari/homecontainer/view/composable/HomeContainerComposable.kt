package com.mercari.homecontainer.view.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.ktx.kotlinktx.Empty
import com.mercari.designsystem.composables.MercariFixedTabRow
import com.mercari.designsystem.composables.MercariTopAppBar
import com.mercari.designsystem.composables.TabItem
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.homecatalogue.view.composables.HomeCatalogueComposable
import com.mercari.model.presentation.shared.HomeCatalogueType
import com.mercari.resources.R
import kotlinx.coroutines.launch

@Composable
internal fun HomeContainerComposable() {
    Scaffold(
        modifier = Modifier.testTag(String.Empty),
        topBar = {
            MercariTopAppBar(titleRes = R.string.app_name)
        },
        content = { paddingValues ->
            HomeContainerContent(modifier = Modifier.padding(paddingValues))
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeContainerContent(
    modifier: Modifier = Modifier
) {
    var tabPosition by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }.collect { currentPage ->
            tabPosition = currentPage
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        MercariFixedTabRow(
            tabItems = tabItems,
            selectedTabPosition = tabPosition,
            onTabChange = { position ->
                tabPosition = position
                scope.launch {
                    pagerState.animateScrollToPage(tabPosition)
                }
            }
        )

        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight(),
            pageCount = tabItems.size,
            state = pagerState
        ) { currentPage ->
            val catalogueType = when (currentPage) {
                0 -> HomeCatalogueType.ManCatalogue
                1 -> HomeCatalogueType.WomenCatalogue
                else -> HomeCatalogueType.AllCatalogue
            }
            HomeCatalogueComposable(catalogueType = catalogueType)
        }
    }
}

private val tabItems: List<TabItem>
    get() = listOf(
        TabItem("Man", R.drawable.ic_man_shirt),
        TabItem("Women", R.drawable.ic_women_dress),
        TabItem("All", R.drawable.ic_all)
    )

/**
 *   ----- Previews -----
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeContainerComposable() = MercariTheme {
    HomeContainerComposable()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeContainerContent() = MercariTheme {
    HomeContainerContent()
}
