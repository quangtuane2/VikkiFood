package com.example.vikkifood.Activity.pages

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
// Thêm import cho các biểu tượng từ resource
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vikkifood.Activity.Dashboard.MainActivity
import com.example.vikkifood.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val authState by authViewModel.authState.observeAsState()
    val currentUser = FirebaseAuth.getInstance().currentUser

    // Kiểm tra xem người dùng đã đăng nhập chưa
    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            // Sử dụng Intent thay vì Navigation Component
            val intent = Intent(context, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông tin cá nhân") },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        authViewModel.signout()
                        // Sử dụng Intent thay vì Navigation Component
                        val intent = Intent(context, AuthActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Đăng xuất"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Phần avatar và thông tin cơ bản
            ProfileHeader(currentUser?.email ?: "")

            Spacer(modifier = Modifier.height(24.dp))

            // Các mục thông tin
            ProfileMenuItem(
                icon = Icons.Default.Person,
                title = "Thông tin cá nhân",
                subtitle = "Cập nhật thông tin cá nhân của bạn"
            )

            ProfileMenuItem(
                icon = Icons.Default.LocationOn,
                title = "Địa chỉ giao hàng",
                subtitle = "Quản lý địa chỉ giao hàng của bạn"
            )

            // Giữ nguyên các icon đã vẽ
            ProfileMenuItemWithDrawable(
                iconRes = R.drawable.ic_credit_card,
                title = "Phương thức thanh toán",
                subtitle = "Quản lý thẻ và phương thức thanh toán"
            )

            ProfileMenuItemWithDrawable(
                iconRes = R.drawable.ic_shopping_bag,
                title = "Lịch sử đơn hàng",
                subtitle = "Xem các đơn hàng đã đặt trước đây"
            )

            ProfileMenuItem(
                icon = Icons.Default.Favorite,
                title = "Món ăn yêu thích",
                subtitle = "Xem danh sách món ăn yêu thích của bạn"
            )

            ProfileMenuItem(
                icon = Icons.Default.Settings,
                title = "Cài đặt",
                subtitle = "Thay đổi cài đặt ứng dụng"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nút đăng xuất
            Button(
                onClick = {
                    authViewModel.signout()
                    // Sử dụng Intent thay vì Navigation Component
                    val intent = Intent(context, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Đăng xuất", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ProfileHeader(email: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        // Avatar - Sử dụng default_avatar từ drawable
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFFFF5722), CircleShape),
                contentScale = ContentScale.Crop
            )

            // Nút chỉnh sửa ảnh đại diện
            FloatingActionButton(
                onClick = { /* Xử lý thay đổi ảnh đại diện */ },
                modifier = Modifier.size(32.dp),
                containerColor = Color(0xFFFF5722),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Chỉnh sửa",
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tên người dùng
        Text(
            text = "Người dùng VikkiFood",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Email
        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

// Giữ nguyên hàm ProfileMenuItem cho các biểu tượng vector
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Card(
        onClick = { /* Xử lý khi nhấp vào mục */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFFFF5722),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Nội dung
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Icon mũi tên
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

// Giữ nguyên hàm cho các biểu tượng từ drawable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileMenuItemWithDrawable(
    iconRes: Int,
    title: String,
    subtitle: String
) {
    Card(
        onClick = { /* Xử lý khi nhấp vào mục */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon từ drawable
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color(0xFFFF5722),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Nội dung
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Icon mũi tên
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
