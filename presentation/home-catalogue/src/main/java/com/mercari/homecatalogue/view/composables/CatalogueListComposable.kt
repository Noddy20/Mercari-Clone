package com.mercari.homecatalogue.view.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mercari.designsystem.composables.MercariBaseCard
import com.mercari.designsystem.composables.MercariPriceTagText
import com.mercari.designsystem.composables.MercariProductItemImage
import com.mercari.designsystem.composables.MercariSoldItemText
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.Padding2XDp
import com.mercari.designsystem.theme.PaddingXDp
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.resources.R

internal val CatalogueItemAdaptiveMinSize: Dp
    get() = 160.dp

@Composable
internal fun CatalogueListComposable(
    catalogueList: List<ProductCatalogueItem>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = Padding2XDp,
            vertical = Padding2XDp
        ),
        columns = GridCells.Adaptive(CatalogueItemAdaptiveMinSize)
    ) {
        items(catalogueList) { catalogueItem ->
            CatalogueItemComposable(item = catalogueItem)
        }
    }
}

@Composable
private fun CatalogueItemComposable(
    item: ProductCatalogueItem
) {
    MercariBaseCard(
        modifier = Modifier
            .padding(horizontal = PaddingXDp, vertical = PaddingXDp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = CatalogueItemAdaptiveMinSize)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (refSoldOutText, refPriceTagText) = createRefs()

            MercariProductItemImage(
                modifier = Modifier.fillMaxSize(),
                url = item.photoUrl
            )

            if (item.status == ProductCatalogueItem.Status.SOLD_OUT) {
                MercariSoldItemText(
                    text = stringResource(R.string.sold),
                    modifier = Modifier
                        .constrainAs(refSoldOutText) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )
            }

            MercariPriceTagText(
                text = item.priceFormatted,
                modifier = Modifier
                    .constrainAs(refPriceTagText) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

/**
 *   ----- Previews -----
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCatalogueListComposable() = MercariTheme {
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

    CatalogueListComposable(catalogueList = list)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCatalogueItemComposable() = MercariTheme {
    CatalogueItemComposable(
        item = ProductCatalogueItem(
            id = "random_id",
            name = "Mercari Product",
            price = 200,
            priceFormatted = "200 ¥",
            photoUrl = "https://dummyimage.com/400x400/000/fff?text=men45",
            status = ProductCatalogueItem.Status.SOLD_OUT
        )
    )
}
