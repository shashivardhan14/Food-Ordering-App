package com.example.project111.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.project111.R
import com.example.project111.domain.FoodModel
import com.example.project111.screens.itemsList.dashboard.MyBottomBar

@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onOrderClick: () -> Unit
) {
    val cartItems = listOf(
        FoodModel(
            Title = "Korean_BBQ_Short_Ribs",
            Price = 22.99,
            Star = 4.4,
            ImagePath = "https://res.cloudinary.com/ddplve8tv/image/upload/v1767939122/Korean_BBQ_Short_Ribs_xxjxhy.jpg",
            numberInCart = 2
        ),
        FoodModel(
            Title = "BBQ_Chicken_Delight",
            Price = 13.99,
            Star = 4.6,
            ImagePath = "https://res.cloudinary.com/ddplve8tv/image/upload/v1767937918/BBQ_Chicken_Delight_ugtykz.jpg",
            numberInCart = 1
        )
    )

    val subtotal = cartItems.sumOf { it.Price * it.numberInCart }
    val deliveryFee = 5.0
    val total = subtotal + deliveryFee

    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onFavoriteClick = onFavoriteClick,
                onOrderClick = onOrderClick,
                onCartClick = { /* Already on Cart */ }
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
                val (backBtn, titleTxt) = createRefs()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(ref = titleTxt) { centerTo(parent) },
                    textAlign = TextAlign.Center,
                    text = "My Cart",
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

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(cartItems) { item ->
                    CartItem(item)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.black3), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(24.dp)
            ) {
                PriceRow("Subtotal", "$${String.format("%.2f", subtotal)}")
                PriceRow("Delivery Fee", "$${String.format("%.2f", deliveryFee)}")
                Spacer(modifier = Modifier.height(8.dp))
                PriceRow("Total", "$${String.format("%.2f", total)}", isTotal = true)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { /* Proceed to Checkout */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.orange))
                ) {
                    Text(text = "Check out", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CartItem(item: FoodModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.black3), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.ImagePath,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.Title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "$${item.Price}", color = Color.Gray, fontSize = 14.sp)
        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "-",
                color = Color.White,
                modifier = Modifier
                    .background(colorResource(R.color.black2), RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .clickable { }
            )
            Text(
                text = "${item.numberInCart}",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "+",
                color = Color.White,
                modifier = Modifier
                    .background(colorResource(R.color.orange), RoundedCornerShape(4.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .clickable { }
            )
        }
    }
}

@Composable
fun PriceRow(label: String, value: String, isTotal: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = label,
            color = if (isTotal) Color.White else Color.Gray,
            fontSize = if (isTotal) 18.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            color = if (isTotal) colorResource(R.color.orange) else Color.White,
            fontSize = if (isTotal) 20.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(
        onBackClick = {},
        onHomeClick = {},
        onProfileClick = {},
        onFavoriteClick = {},
        onOrderClick = {}
    )
}
