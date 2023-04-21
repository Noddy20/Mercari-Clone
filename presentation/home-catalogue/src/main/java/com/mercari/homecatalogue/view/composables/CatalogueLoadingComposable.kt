package com.mercari.homecatalogue.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mercari.designsystem.composables.MercariBaseCard
import com.mercari.designsystem.composables.MercariShimmerBrush
import com.mercari.designsystem.theme.Padding2XDp
import com.mercari.designsystem.theme.PaddingXDp

@Composable
fun CatalogueLoadingComposable() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = Padding2XDp,
            vertical = Padding2XDp
        ),
        columns = GridCells.Adaptive(CatalogueItemAdaptiveMinSize)
    ) {
        items(4) {
            MercariBaseCard(
                modifier = Modifier
                    .padding(horizontal = PaddingXDp, vertical = PaddingXDp)
                    .fillMaxWidth()
                    .height(CatalogueItemAdaptiveMinSize)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                        brush = MercariShimmerBrush(
                            targetValue = 1300f,
                            showShimmer = true
                        )
                    )
                )
            }
        }
    }
}