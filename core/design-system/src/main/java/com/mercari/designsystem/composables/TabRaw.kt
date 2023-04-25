package com.mercari.designsystem.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabPosition
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.mercari.resources.R
import com.mercari.designsystem.theme.ColorInactive
import com.mercari.designsystem.theme.ColorFontTitleLight
import com.mercari.designsystem.theme.ColorMercariRed
import com.mercari.designsystem.theme.MercariTheme
import com.mercari.designsystem.theme.OneDp

private fun tabIndicator(
    selectedTabPosition: Int
) = @Composable { tabPositions: List<TabPosition> ->
    TabRowDefaults.Indicator(
        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabPosition]),
        height = OneDp,
        color = ColorMercariRed
    )
}

private fun tabText(
    title: String,
    isSelectedTab: Boolean
) = @Composable {
    MercariCaptionText(
        text = title,
        color = if (isSelectedTab) {
            MaterialTheme.colors.onPrimary
        } else {
            ColorInactive
        }
    )
}

private fun tabIcon(
    title: String,
    @DrawableRes icon: Int,
    isSelectedTab: Boolean
) = @Composable {
    MercariSmallIcon(
        painter = painterResource(id = icon),
        contentDescription = title,
        tint = if (isSelectedTab) {
            MaterialTheme.colors.onPrimary
        } else {
            ColorInactive
        }
    )
}

@Composable
private fun MercariTab(
    isSelectedTab: Boolean,
    indexOfTab: Int,
    tabItem: TabItem,
    onTabChange: (position: Int) -> Unit
) {
    Tab(
        text = tabText(
            title = tabItem.title,
            isSelectedTab = isSelectedTab
        ),
        selected = isSelectedTab,
        onClick = {
            onTabChange(indexOfTab)
        },
        icon = tabIcon(
            title = tabItem.title,
            icon = tabItem.icon,
            isSelectedTab = isSelectedTab
        )
    )
}

@Composable
fun MercariFixedTabRow(
    tabItems: List<TabItem>,
    selectedTabPosition: Int,
    onTabChange: (position: Int) -> Unit
) {
    TabRow(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(AppBarDefaults.TopAppBarElevation)
            .zIndex(1f),
        selectedTabIndex = selectedTabPosition,
        divider = {},
        indicator = tabIndicator(selectedTabPosition)
    ) {
        tabItems.forEachIndexed { index, tabItem ->
            MercariTab(
                isSelectedTab = selectedTabPosition == index,
                indexOfTab = index,
                tabItem = tabItem,
                onTabChange = onTabChange
            )
        }
    }
}

class TabItem(val title: String, @DrawableRes val icon: Int)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariTab() = MercariTheme {
    MercariTab(
        isSelectedTab = true,
        indexOfTab = 0,
        tabItem = TabItem("Man", R.drawable.ic_man_shirt),
        onTabChange = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMercariFixedTabRow() = MercariTheme {
    MercariFixedTabRow(
        tabItems = listOf(
            TabItem("Man", R.drawable.ic_man_shirt),
            TabItem("Women", R.drawable.ic_women_dress),
            TabItem("All", R.drawable.ic_all)
        ),
        selectedTabPosition = 1,
        onTabChange = {}
    )
}