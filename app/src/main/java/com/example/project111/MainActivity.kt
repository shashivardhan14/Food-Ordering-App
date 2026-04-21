package com.example.project111

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project111.navigation.Screen
import com.example.project111.screens.dashboard.MainScreen
import com.example.project111.screens.detailFood.DetailScreen
import com.example.project111.screens.itemsList.ItemListScreen
import com.example.project111.screens.cart.CartScreen
import com.example.project111.screens.favorite.FavoriteScreen
import com.example.project111.screens.order.OrderScreen
import com.example.project111.screens.profile.ProfileScreen
import com.example.project111.screens.itemsList.dashboard.MyBottomBar
import com.example.project111.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost()

        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val vm: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            MainScreen(
                viewModel = vm,
                onOpenItems = { id, title ->
                    navController.navigate(Screen.Items.path(id, title))
                },
                onOpenDetail = { food ->
                    vm.selectedFood(food)
                    navController.navigate(Screen.Detail.route)
                },
                onOpenProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onOpenFavorite = {
                    navController.navigate(Screen.Favorite.route)
                },
                onOpenOrder = {
                    navController.navigate(Screen.Order.route)
                },
                onOpenCart = {
                    navController.navigate(Screen.Cart.route)
                }


            )
        }
        composable(
            route = Screen.Items.route, arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""

            ItemListScreen(
                viewModel = vm,
                id = id,
                title = title,
                onBackClick = { navController.navigateUp() },
                onOpenDetails = { foodModel ->

                    vm.selectedFood(foodModel)
                    navController.navigate(Screen.Detail.route) {
                        launchSingleTop = true
                    }


                }
            )

        }
        composable(route = Screen.Detail.route) {
            val food by vm.selectedFood.collectAsState()
            if (food == null) {
                return@composable
            }
            DetailScreen(
                item = food!!,
                onBackClick = { navController.navigateUp() },
                onAddToCartClick = {},
                viewModel = vm,
                onOpenDetail = { next ->
                    vm.selectedFood(next)
                    navController.navigate(Screen.Detail.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onBackClick = { navController.navigateUp() },
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onFavoriteClick = { navController.navigate(Screen.Favorite.route) },
                onOrderClick = { navController.navigate(Screen.Order.route) }
            )
        }
        composable(route = Screen.Favorite.route) {
            val food by vm.selectedFood.collectAsState()
            FavoriteScreen(
                onBackClick = { navController.navigateUp() },
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onOrderClick = { navController.navigate(Screen.Order.route) },
                onOpenDetail = { favoriteFood ->
                    vm.selectedFood(favoriteFood)
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(route = Screen.Order.route) {
            OrderScreen(
                onBackClick = { navController.navigateUp() },
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onFavoriteClick = { navController.navigate(Screen.Favorite.route) }
            )
        }
        composable(route = Screen.Cart.route) {
            CartScreen(
                onBackClick = { navController.navigateUp() },
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onFavoriteClick = { navController.navigate(Screen.Favorite.route) },
                onOrderClick = { navController.navigate(Screen.Order.route) }
            )
        }
    }

}

@Composable
fun BlankScreen(
    title: String,
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onOrderClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onFavoriteClick = onFavoriteClick,
                onOrderClick = onOrderClick
            )
        },
        backgroundColor = colorResource(id = R.color.black3)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, color = Color.White, fontSize = 24.sp)
        }
    }
}
