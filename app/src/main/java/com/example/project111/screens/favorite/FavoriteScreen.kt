package com.example.project111.screens.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.project111.R
import com.example.project111.domain.FoodModel
import com.example.project111.screens.itemsList.ItemsList
import com.example.project111.screens.itemsList.dashboard.MyBottomBar

@Composable
fun FavoriteScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onOrderClick: () -> Unit,
    onOpenDetail: (FoodModel) -> Unit
) {
    // Sample Data
    val favoriteItems = listOf(
        FoodModel(
            Title = "Pepperoni Pizza",
            Price = 12.5,
            Star = 4.5,
            ImagePath = "https://res.cloudinary.com/ddplve8tv/image/upload/v1767939357/Pepperoni_Lovers_ynllan.jpg",
            TimeValue = 20
        ),
        FoodModel(
            Title = "Classic Beef Burger",
            Price = 8.99,
            Star = 4.5,
            ImagePath = "https://res.cloudinary.com/ddplve8tv/image/upload/v1767938778/Classic_Beef_Burger_jiiqss.jpg",
            TimeValue = 15
        ),
        FoodModel(
            Title = "Original Crispy Chicken",
            Price = 9.99,
            Star = 4.7,
            ImagePath = "https://res.cloudinary.com/ddplve8tv/image/upload/v1767939321/Original_Crispy_Chicken_dcd6ye.jpg",
            TimeValue = 18
        )
    )

    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onFavoriteClick = { /* Already on Favorite */ },
                onOrderClick = onOrderClick
            )
        },
        backgroundColor = colorResource(R.color.black2)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ConstraintLayout(modifier = Modifier.padding(top = 36.dp, start = 16.dp, end = 16.dp)) {
                val (backBtn, cartTxt) = createRefs()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(ref = cartTxt) { centerTo(parent) },
                    textAlign = TextAlign.Center,
                    text = "Favorites",
                    fontSize = 25.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onBackClick() }
                        .constrainAs(ref = backBtn) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                )
            }

            ItemsList(
                items = favoriteItems,
                onItemClick = onOpenDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(
        onBackClick = {},
        onHomeClick = {},
        onProfileClick = {},
        onOrderClick = {},
        onOpenDetail = {}
    )
}
