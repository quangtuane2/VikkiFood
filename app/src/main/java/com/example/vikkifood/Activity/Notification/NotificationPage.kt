package com.example.vikkifood.Activity.Notification

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vikkifood.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(onBackClick: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            NotificationTopBar(
                onBackClick = onBackClick,
                onBellClick = { isVisible = !isVisible },
                isBellActive = isVisible
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isVisible) {
                NotificationList()
            }
        }
    }
}


@Composable
fun NotificationList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Notifications", color = Color.Green, style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(16.dp))

        NotificationItem(
            icon = R.drawable.star,
            text = "Your order has been Canceled Successfully"
        )
        NotificationItem(
            icon = R.drawable.cart,
            text = "Order has been taken by the driver"
        )
        NotificationItem(
            icon = R.drawable.default_avatar,
            text = "Congrats Your Order Placed"
        )
    }
}

@Composable
fun NotificationItem(icon: Int, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text)
        }
    }
}
