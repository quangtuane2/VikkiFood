package com.example.vikkifood.Activity.Dashboard

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vikkifood.Activity.Cart.CartActivity
import com.example.vikkifood.Activity.pages.ProfileActivity
import com.example.vikkifood.R
import com.example.vikkifood.Activity.favourite.FavouriteActivity

@Composable
@Preview
fun MyBottomBar(){
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        backgroundColor = colorResource(R.color.grey),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEach{bottomMenuItem ->
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.lable),
                onClick = {
                    selectedItem = bottomMenuItem.lable
                    when (bottomMenuItem.lable) {
                        "Cart" -> {
                            context.startActivity(Intent(context, CartActivity::class.java))
                        }
                        "Favorite" -> {
                            // Chuyển hướng đến FavouriteActivity
                            context.startActivity(Intent(context, FavouriteActivity::class.java))
                        }
                        "Profile" -> {
                            // Chuyển hướng đến ProfileActivity
                            context.startActivity(Intent(context, ProfileActivity::class.java))
                        }
                        else -> {
                            Toast.makeText(context, bottomMenuItem.lable, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                icon = {
                    Icon(painter = bottomMenuItem.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp))
                }
            )
        }
    }
}

data class BottomMenuItem(
    val lable:String, val icon:Painter
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(lable = "Home", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomMenuItem(lable = "Cart", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(BottomMenuItem(lable = "Favorite", icon = painterResource(R.drawable.btn_3)))
    bottomMenuItemList.add(BottomMenuItem(lable = "Order", icon = painterResource(R.drawable.btn_4)))
    bottomMenuItemList.add(BottomMenuItem(lable = "Profile", icon = painterResource(R.drawable.btn_5)))

    return bottomMenuItemList
}
