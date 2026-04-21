package com.example.project111.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project111.R
import com.example.project111.screens.itemsList.dashboard.MyBottomBar

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onOrderClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            MyBottomBar(
                onHomeClick = onHomeClick,
                onFavoriteClick = onFavoriteClick,
                onOrderClick = onOrderClick
            )
        },
        backgroundColor = colorResource(id = R.color.black3)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            // Header with Back Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onBackClick() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Profile",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1.2f))
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Profile Image
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.orange).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Nancy",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "nancy@example.com",
                color = Color.Gray,
                fontSize = 14.sp
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Menu Items
            ProfileMenuItem(icon = painterResource(id = R.drawable.btn_5), title = "Personal Info") {}
            ProfileMenuItem(icon = painterResource(id = R.drawable.btn_4), title = "My Orders") {}
            ProfileMenuItem(icon = painterResource(id = R.drawable.btn_3), title = "My Favorites") {}
            ProfileMenuItem(icon = painterResource(id = R.drawable.btn_2), title = "Settings") {}
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Logout
            ProfileMenuItem(
                icon = painterResource(id = R.drawable.btn_1),
                title = "Logout", 
                titleColor = colorResource(id = R.color.orange),
                showArrow = false
            ) {}
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: Painter,
    title: String,
    titleColor: Color = Color.White,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(colorResource(id = R.color.black3), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = titleColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onBackClick = {})
}
