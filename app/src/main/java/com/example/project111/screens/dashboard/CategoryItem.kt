package com.example.project111.screens.itemsList.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project111.domain.CategoryModel
import com.example.project111.R

@Composable
fun CategoryItem(
    category: CategoryModel,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.black3),
                shape = RoundedCornerShape(13.dp)
            )
            .clickable(onClick = onItemClick)
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.ImagePath,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            onState = { state ->
                if (state is coil.compose.AsyncImagePainter.State.Error) {
                    println("Coil Error: ${state.result.throwable}")
                }
            }
        )
        Text(
            text = category.Name.toString(),
            color = colorResource(R.color.white),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun CategorySection(
    categories: SnapshotStateList<CategoryModel>,
    showCategoryLoading: Boolean,
    onCategoryClick: (CategoryModel) -> Unit
) {
    if (showCategoryLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator(color = colorResource(R.color.orange)) }

    } else {
        val rows = categories.chunked(3)
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEach { categoryModel ->
                        CategoryItem(
                            category = categoryModel,
                            modifier = Modifier.weight(1f)
                            .padding(horizontal = 12.dp),
                            onItemClick = { onCategoryClick(categoryModel) }

                        )
                    }
                    if (row.size < 3) {
                        repeat(3-row.size){
                            Spacer(modifier = Modifier.weight(1f))

                        }

                    }
                }
            }
        }
    }
}