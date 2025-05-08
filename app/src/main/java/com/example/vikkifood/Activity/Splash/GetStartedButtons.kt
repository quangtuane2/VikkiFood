package com.example.vikkifood.Activity.Splash

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vikkifood.R

@Composable
@Preview
fun GetStartedButton(onClick: () -> Unit = {}, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
//                ,contentColor = Color.Black
        ),
            shape = RoundedCornerShape(50.dp)
                ,modifier = modifier
                .padding(end = 16.dp)
                .fillMaxWidth(0.35f)
                .border(1.dp, Color.White, shape = RoundedCornerShape(50.dp))
                .height(50.dp)
        ) {
            Text(text = "Signup",
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Button(
            onClick = {onClick()},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orange)
//                ,contentColor = Color.Black
            ),
            shape = RoundedCornerShape(50.dp)
            ,modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Get Started",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
