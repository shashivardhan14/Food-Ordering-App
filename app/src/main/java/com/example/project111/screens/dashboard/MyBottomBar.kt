package com.example.project111.screens.itemsList.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project111.R

@Composable
fun MyBottomBar(
    onProfileClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onOrderClick: () -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    val bottomMenuItemList = prepareBottomMenu()
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        backgroundColor = colorResource(id = R.color.black3),
        elevation = 3.dp

    ) {
        bottomMenuItemList.forEach { bottomMenuItem ->
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.lable),
                onClick = {
                    selectedItem = bottomMenuItem.lable
                    when (bottomMenuItem.lable) {
                        "Home" -> onHomeClick()
                        "Profile" -> onProfileClick()
                        "Favorite" -> onFavoriteClick()
                        "Order" -> onOrderClick()
                        "Cart" -> onCartClick()
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.7f),
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp),
                        tint = Color.Unspecified
                    )

                }
            )

        }


    }


}

data class BottomMenuItem(
    val lable: String, val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(lable = "Home", icon = painterResource(id = R.drawable.btn_1)),
        BottomMenuItem(lable = "Cart", icon = painterResource(id = R.drawable.btn_2)),
        BottomMenuItem(lable = "Favorite", icon = painterResource(id = R.drawable.btn_3)),
        BottomMenuItem(lable = "Order", icon = painterResource(id = R.drawable.btn_4)),
        BottomMenuItem(lable = "Profile", icon = painterResource(id = R.drawable.btn_5)),


        )

}