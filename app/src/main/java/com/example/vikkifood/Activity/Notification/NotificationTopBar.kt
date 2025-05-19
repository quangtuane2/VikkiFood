package com.example.vikkifood.Activity.Notification

import androidx.compose.foundation.Image
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.vikkifood.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationTopBar(
    onBackClick: () -> Unit,
    onBellClick: () -> Unit,
    isBellActive: Boolean
) {
    TopAppBar(
        title = { Text("Notifications") },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.bell_icon),
                contentDescription = "Bell",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(34.dp)
                    .clickable { onBellClick() }
            )
        }
    )
}

