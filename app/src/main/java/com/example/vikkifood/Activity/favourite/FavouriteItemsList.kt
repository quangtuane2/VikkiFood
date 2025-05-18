package com.example.vikkifood.Activity.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.R
import com.example.vikkifood.Repository.FavouriteRepository
import com.example.vikkifood.ViewModel.FavouriteViewModel

@Composable
fun FavouriteItemsList(items: List<FoodModel>, viewModel: FavouriteViewModel) {
    LazyColumn {
        items(items) { item ->
            FavouriteItem(
                item = item,
                onRemoveClick = {
                    viewModel.removeFromFavourite(item)
                }
            )
        }
    }
}

@Composable
fun FavouriteItem(item: FoodModel, onRemoveClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.ImagePath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            androidx.compose.foundation.layout.Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = item.Title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.darkPurple)
                )

                Text(
                    text = "${item.Price} đ",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.pink)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.fav_icon),
                contentDescription = "Remove from favourites",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable { onRemoveClick() }
            )
        }
    }
}
