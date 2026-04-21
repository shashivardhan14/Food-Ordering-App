package com.example.project111.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project111.viewModel.MainViewModel
import com.example.project111.R
import com.example.project111.domain.CategoryModel
import com.example.project111.domain.FoodModel
import com.example.project111.screens.itemsList.dashboard.CategorySection
import com.example.project111.screens.itemsList.dashboard.FoodItemCardGrid
import com.example.project111.screens.itemsList.dashboard.MyBottomBar
import com.example.project111.screens.itemsList.dashboard.TopBar

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onOpenItems: (id: String, title: String) -> Unit,
    onOpenDetail: (FoodModel) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenFavorite: () -> Unit,
    onOpenOrder: () -> Unit,
    onOpenCart: () -> Unit
) {
    val categories by viewModel.loadCategory().observeAsState(emptyList())
    val bestFood by viewModel.loadBestFood().observeAsState(emptyList())

    val categoriesList = remember(categories) { categories.toMutableStateList() }

    MainScreenContent(
        categories = categoriesList,
        bestFood = bestFood,
        showCategoryLoading = categories.isEmpty(),
        showBestFoodLoading = bestFood.isEmpty(),
        onOpenItems = onOpenItems,
        onOpenDetail = onOpenDetail,
        onOpenProfile = onOpenProfile,
        onOpenFavorite = onOpenFavorite,
        onOpenOrder = onOpenOrder,
        onOpenCart = onOpenCart
    )
}

@Composable
fun MainScreenContent(
    categories: SnapshotStateList<CategoryModel>,
    bestFood: List<FoodModel>,
    showCategoryLoading: Boolean,
    showBestFoodLoading: Boolean,
    onOpenItems: (id: String, title: String) -> Unit,
    onOpenDetail: (FoodModel) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenFavorite: () -> Unit,
    onOpenOrder: () -> Unit,
    onOpenCart: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = { /* Already on Home */ },
                onProfileClick = onOpenProfile,
                onFavoriteClick = onOpenFavorite,
                onOrderClick = onOpenOrder,
                onCartClick = onOpenCart
            )
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black3))
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2) })
            { TopBar() }
            item(span = { GridItemSpan(2) }) {
                CategorySection(
                    categories = categories,
                    showCategoryLoading = showCategoryLoading,
                    onCategoryClick = { cat ->
                        onOpenItems(cat.Id.toString(), cat.Name ?: "")
                    }
                )
            }
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Food for you ",
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )

            }
            if (showBestFoodLoading) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = colorResource(R.color.orange))

                    }
                }

            } else {
                items(count = bestFood.size) { index ->
                    FoodItemCardGrid(
                        item = bestFood[index],
                        onClick = { onOpenDetail(bestFood[index]) }
                    )

                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val categories = listOf(
        CategoryModel(1, "Pizza", ""),
        CategoryModel(2, "Burger", ""),
        CategoryModel(3, "Chicken", ""),
        CategoryModel(4, "Drink", ""),
        CategoryModel(5, "Donut", ""),
        CategoryModel(6, "Bakery", "")
    ).toMutableStateList()

    val bestFood = listOf(
        FoodModel(Title = "Pepperoni Pizza", Price = 12.5, Star = 4.5, ImagePath = ""),
        FoodModel(Title = "Cheese Burger", Price = 8.9, Star = 4.2, ImagePath = ""),
        FoodModel(Title = "Grilled Chicken", Price = 15.0, Star = 4.8, ImagePath = ""),
        FoodModel(Title = "Ice Cream", Price = 5.5, Star = 4.0, ImagePath = "")
    )

    MainScreenContent(
        categories = categories,
        bestFood = bestFood,
        showCategoryLoading = false,
        showBestFoodLoading = false,
        onOpenItems = { _, _ -> },
        onOpenDetail = {},
        onOpenProfile = {},
        onOpenFavorite = {},
        onOpenOrder = {},
        onOpenCart = {}
    )
}
