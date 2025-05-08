package com.example.vikkifood.Activity.Cart

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vikkifood.R

@Composable
fun DeliveryInfoBox(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(color = colorResource(R.color.grey), shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        InfoItem(
            title = "Your Delivery Address",
            content = "NY-downtow-no97",
            icon = painterResource(R.drawable.location)
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))
        InfoItem(
            title = "Payment Method",
            content = "Cash",
            icon = painterResource(R.drawable.credit_card)
        )
    }
    Button(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.orange)
        ),
        modifier = Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "Place Order",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun InfoItem(title: String, content: String, icon: Painter){
    Column{
        Text(text = title, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = icon,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = content,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}