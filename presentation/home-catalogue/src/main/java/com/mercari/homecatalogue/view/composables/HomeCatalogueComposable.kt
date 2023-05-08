package com.mercari.homecatalogue.view.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mercari.designsystem.composables.MercariError
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.homecatalogue.viewmodel.HomeCatalogueContract
import com.mercari.homecatalogue.viewmodel.HomeCatalogueViewModel
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.presentation.shared.HomeCatalogueType

@Composable
fun HomeCatalogueComposable(
    catalogueType: HomeCatalogueType
) {
    val viewModel: HomeCatalogueContract = hiltViewModel<HomeCatalogueViewModel>(key = catalogueType.toString())
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun fetchCatalogue() {
        val intent = when (catalogueType) {
            HomeCatalogueType.ManCatalogue -> HomeCatalogueContract.Intent.LoadManCatalogue
            HomeCatalogueType.WomenCatalogue -> HomeCatalogueContract.Intent.LoadWomenCatalogue
            HomeCatalogueType.AllCatalogue -> HomeCatalogueContract.Intent.LoadAllCatalogue
        }
        viewModel.processIntent(intent)
    }

    LaunchedEffect(key1 = catalogueType) {
        if (viewModel.state.value !is HomeCatalogueContract.State.CatalogueLoaded) {
            fetchCatalogue()
        }
    }

    HomeCatalogueComposableContent(state) {
        fetchCatalogue()
    }
}

@Composable
private fun HomeCatalogueComposableContent(
    state: HomeCatalogueContract.State,
    onRetryClick: () -> Unit
) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            HomeCatalogueContract.State.CatalogueLoading -> {
                CatalogueLoadingComposable()
            }
            is HomeCatalogueContract.State.CatalogueLoaded -> {
                CatalogueListComposable(catalogueList = state.catalogue)
            }
            is HomeCatalogueContract.State.CatalogueLoadError -> {
                MercariError(
                    modifier = Modifier.align(Alignment.Center),
                    title = state.errorData.getTitle {
                        context.getString(it)
                    },
                    message = state.errorData.getMessage {
                        context.getString(it)
                    },
                    allowRetry = state.errorData.allowRetry,
                    onRetryClick = onRetryClick
                )
            }
        }
    }
}

/**
 *   ----- Previews -----
 */

// Default Preview composable in Theme design-system needed

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeCatalogueComposable() = MercariTheme {
    val item = ProductCatalogueItem(
        id = "random_id",
        name = "Mercari Product",
        price = 200,
        priceFormatted = "200 ¥",
        photoUrl = "https://dummyimage.com/400x400/000/fff?text=men45",
        status = ProductCatalogueItem.Status.SOLD_OUT
    )
    val list = arrayListOf(item)
    list.add(item)
    list.add(item)
    list.add(item)
    list.add(item)

    HomeCatalogueComposableContent(HomeCatalogueContract.State.CatalogueLoaded(list)) {
        // Retry
    }
}
