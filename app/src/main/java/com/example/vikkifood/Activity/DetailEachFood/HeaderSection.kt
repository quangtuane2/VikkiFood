package com.example.vikkifood.Activity.DetailEachFood

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.R

@Composable
fun HeaderSection(
    item: FoodModel,
    numberInCart: Int,
    isFavourite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(570.dp)
        .padding(bottom = 16.dp)
    ) {
        val (back, fav, mainImage, arcImg, title, detailRow, numberRow) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(model = item.ImagePath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(
                    RoundedCornerShape(
                    bottomStart = 30.dp, bottomEnd = 30.dp
                    )
                )
                .constrainAs(mainImage){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )
        Image(
            painter = painterResource(R.drawable.arc_bg),
            contentDescription = null,
            modifier = Modifier
                .height(190.dp)
                .constrainAs(arcImg){
                    top.linkTo(mainImage.bottom, margin = (-64).dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )

        BackButton(onBackClick, Modifier.constrainAs(back){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        FavoriteButton(
            isFavourite = isFavourite, // Truyền trạng thái yêu thích
            onClick = onFavoriteClick,
            modifier = Modifier.constrainAs(fav){
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = item.Title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.darkPurple),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(title){
                    top.linkTo(arcImg.top, margin = 32.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )

        RowDetail(item, Modifier.constrainAs(detailRow){
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        NumberRow(
            item = item,
            numberInCart = numberInCart,
            onIncrement = onIncrement,
            onDecrement = onDecrement,
            Modifier.constrainAs(numberRow){
                top.linkTo(detailRow.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
private fun BackButton(onclick: () -> Unit, modifier: Modifier = Modifier){
    Image(
        painter = painterResource(R.drawable.back),
        contentDescription = null,
        modifier = modifier
            .padding(start = 16.dp, top = 48.dp)
            .clickable { onclick() }
    )
}

@Composable
private fun FavoriteButton(
    isFavourite: Boolean, // Thêm tham số này
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    // Chọn icon dựa trên trạng thái yêu thích
    val iconRes = if (isFavourite) {
        R.drawable.fav_filled // Bạn cần thêm icon này vào thư mục res/drawable
    } else {
        R.drawable.fav_icon
    }

    Image(
        painter = painterResource(iconRes),
        contentDescription = if (isFavourite) "Remove from favorites" else "Add to favorites",
        modifier = modifier
            .padding(end = 16.dp, top = 48.dp)
            .clickable { onClick() }
    )
}