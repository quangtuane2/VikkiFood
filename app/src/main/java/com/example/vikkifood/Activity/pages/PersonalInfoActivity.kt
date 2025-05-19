package com.example.vikkifood.Activity.pages


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.vikkifood.ui.theme.VikkiFoodTheme

class PersonalInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VikkiFoodTheme {
                Surface {
                    PersonalInfoPage()
                }
            }
        }
    }
}
