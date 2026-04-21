package com.example.project111.navigation

import com.example.project111.domain.FoodModel
import kotlinx.coroutines.flow.MutableStateFlow


sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object Items : Screen("itemsList/{id}/{title}") {
        fun path(id: String, title: String) = "itemsList/$id/${
            java.net.URLEncoder.encode(
                title,
                Charsets.UTF_8.name()
            )
        }"
    }
    data object Detail : Screen("detail")
    data object Profile : Screen("profile")
    data object Favorite : Screen("favorite")
    data object Order : Screen("order")
    data object Cart : Screen("cart")





}
