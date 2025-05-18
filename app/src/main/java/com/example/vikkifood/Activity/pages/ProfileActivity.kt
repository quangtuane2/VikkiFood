package com.example.vikkifood.Activity.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

class ProfileActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        // Kiểm tra trạng thái đăng nhập khi khởi tạo
        authViewModel.checkAuthStatus()

        setContent {
            // Sử dụng ProfilePage trực tiếp thay vì thông qua Navigation
            val navController = androidx.navigation.compose.rememberNavController()
            ProfilePage(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}
