package com.example.project111.screens.order

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
import androidx.compose.material.Scaffold
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.project111.R
import com.example.project111.screens.itemsList.dashboard.MyBottomBar

data class OrderModelSample(
    val orderId: String,
    val date: String,
    val totalPrice: Double,
    val status: String,
    val itemsCount: Int
)

@Composable
fun OrderScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val orders = listOf(
        OrderModelSample("#ORD-7721", "20 Oct 2023", 45.50, "Delivered", 3),
        OrderModelSample("#ORD-8812", "18 Oct 2023", 22.00, "Delivered", 1),
        OrderModelSample("#ORD-9905", "15 Oct 2023", 35.20, "Cancelled", 2),
        OrderModelSample("#ORD-1024", "10 Oct 2023", 12.50, "Delivered", 1)
    )

    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = onHomeClick,
                onProfileClick = onProfileClick,
                onFavoriteClick = onFavoriteClick,
                onOrderClick = { /* Already on Order */ }
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
                    text = "My Orders",
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
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(orders) { order ->
                    OrderItem(order)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun OrderItem(order: OrderModelSample) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.black3), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = order.orderId,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = order.status,
                color = if (order.status == "Delivered") colorResource(R.color.green) else colorResource(R.color.orange),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = order.date, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${order.itemsCount} items", color = Color.Gray, fontSize = 14.sp)
        }
        
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 0.5.dp,
            color = Color.Gray.copy(alpha = 0.3f)
        )
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Total Amount:", color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${order.totalPrice}",
                color = colorResource(R.color.orange),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(
        onBackClick = {},
        onHomeClick = {},
        onProfileClick = {},
        onFavoriteClick = {}
    )
}
