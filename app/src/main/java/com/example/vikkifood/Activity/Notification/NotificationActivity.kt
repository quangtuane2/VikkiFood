package com.example.vikkifood.Activity.Notification

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.vikkifood.Activity.BaseActivity

class NotificationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen(onBackClick = { finish() })
        }
    }
}
