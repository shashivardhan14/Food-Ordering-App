package com.example.project111.screens.detailFood

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.project111.domain.FoodModel
import com.example.project111.viewModel.MainViewModel
import com.example.project111.Helper.ManagementCart
import com.example.project111.R


@Composable
fun DetailScreen(
    item: FoodModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    viewModel: MainViewModel,
    onOpenDetail: (FoodModel) -> Unit
) {
    val context = LocalContext.current
    val managementCart = remember { ManagementCart(context) }
    var numberInCart by remember { mutableIntStateOf(item.numberInCart) }

    BackHandler(enabled = true) {
        viewModel.selectedFood(null)
        onBackClick()


    }
    ConstraintLayout {
        val (footer, column) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.black2))
                .verticalScroll(rememberScrollState())
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                .padding(bottom = 100.dp)
        ) {
            HeaderSection(
                item = item,
                onBackClick = {
                    viewModel.selectedFood(null)
                    onBackClick()
                }
            )
            TitleNumberRow(
                item = item,
                numberInCart = numberInCart,
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart
                },
                onDecrement = {
                    if (numberInCart > 1) {
                        numberInCart--
                        item.numberInCart = numberInCart

                    }
                },
            )
            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)

            )
            RowDetail(item)
            DescriptionSection(item.Description)
            RecommendedList(
                viewModel = viewModel,
                onItemClick = onOpenDetail
            )

        }
        FooterSection(
            onAddToCartClick = {
                managementCart.insertItem(item)
                onAddToCartClick()
            },
            totalPrice = (item.Price * numberInCart),
            modifier = Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            }
        )


    }

}
@Preview
@Composable
fun DetailScreenPreview() {
    val item = FoodModel(
        Title = "Pizza",
        Price = 9.99,
    )
}


