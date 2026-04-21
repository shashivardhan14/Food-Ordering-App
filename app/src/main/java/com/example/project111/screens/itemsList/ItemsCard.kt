package com.example.project111.screens.itemsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project111.domain.FoodModel
import com.example.project111.R

@Composable
fun ItemsList(
    items: List<FoodModel>,
    onItemClick: (FoodModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        itemsIndexed(items) { _, item ->
            Items(
                item = item,
                onItemClick = { onItemClick(item) })
        }

    }
}

@Preview
@Composable
fun ItemsListPreview() {
    val items = listOf(
        FoodModel(Title = "Pizza", Price = 12.5, Star = 4.5),
        FoodModel(Title = "Burger", Price = 8.0, Star = 4.2),
        FoodModel(Title = "Salad", Price = 7.2, Star = 4.8)
    )
    ItemsList(items = items, onItemClick = {})

}

@Composable
fun Items(item: FoodModel, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.black3))
            .wrapContentHeight()
            .clickable { onItemClick() }
    ) {
        FoodImage(item = item)
        FoodDetail(item = item)
    }

}

@Composable
fun RowScope.FoodDetail(item: FoodModel) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .weight(1f)
    ) {

        Text(
            text = item.Title,
            color = colorResource(R.color.white),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )
        TimingRow(item.TimeValue)
        RatingBarRow(item.Star)
        PriceRow(item.Price)

    }

}

@Composable
fun PriceRow(price: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "$$price",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "+ Add",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.background(
                color = colorResource(R.color.green),
                shape = RoundedCornerShape(size = 50.dp)
            )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }

}

@Composable
fun RatingBarRow(star: Double) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = null, modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = "$star", color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }

}

@Composable
fun TimingRow(timeValue: Int) {
    Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = null, modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "$timeValue min", color = Color.White, style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
fun FoodImage(item: FoodModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = null,
        modifier = Modifier
            .size(125.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(10.dp)
            ), contentScale = ContentScale.Crop

    )

}