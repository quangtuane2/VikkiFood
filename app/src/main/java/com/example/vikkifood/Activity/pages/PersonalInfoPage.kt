package com.example.vikkifood.Activity.pages

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.auth.FirebaseAuth
import androidx.activity.ComponentActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoPage() {
    val context = LocalContext.current
    val currentUser = FirebaseAuth.getInstance().currentUser

    // Các thông tin cá nhân (có thể lấy từ Firebase hoặc database khác)
    var name by remember { mutableStateOf(currentUser?.displayName ?: "Quang") }
    var phoneNumber by remember { mutableStateOf("0987654321") }
    var address by remember { mutableStateOf("NguHanhSon-DaNang") }
    var email by remember { mutableStateOf(currentUser?.email ?: "user@example.com") }

    // Biến để kiểm soát hiển thị dialog
    var showEditDialog by remember { mutableStateOf(false) }
    // Biến để lưu thông tin đang được chỉnh sửa
    var editingField by remember { mutableStateOf("") }
    var editingValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông tin cá nhân") },
                navigationIcon = {
                    IconButton(onClick = {
                        context.startActivity(Intent(context, ProfileActivity::class.java))
                        if (context is ComponentActivity) {
                            context.finish()
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoSection(title = "Thông tin cơ bản")

            EditableInfoItem(
                label = "Họ và tên",
                value = name,
                onEditClick = {
                    editingField = "Họ và tên"
                    editingValue = name
                    showEditDialog = true
                }
            )

            EditableInfoItem(
                label = "Email",
                value = email,
                onEditClick = {
                    editingField = "Email"
                    editingValue = email
                    showEditDialog = true
                }
            )

            EditableInfoItem(
                label = "Số điện thoại",
                value = phoneNumber,
                onEditClick = {
                    editingField = "Số điện thoại"
                    editingValue = phoneNumber
                    showEditDialog = true
                }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            InfoSection(title = "Địa chỉ")

            EditableInfoItem(
                label = "Địa chỉ hiện tại",
                value = address,
                onEditClick = {
                    editingField = "Địa chỉ hiện tại"
                    editingValue = address
                    showEditDialog = true
                }
            )
        }

        // Dialog chỉnh sửa thông tin
        if (showEditDialog) {
            EditInfoDialog(
                title = "Chỉnh sửa $editingField",
                initialValue = editingValue,
                onDismiss = { showEditDialog = false },
                onConfirm = { newValue ->
                    when (editingField) {
                        "Họ và tên" -> name = newValue
                        "Email" -> email = newValue
                        "Số điện thoại" -> phoneNumber = newValue
                        "Địa chỉ hiện tại" -> address = newValue
                    }
                    // Ở đây bạn có thể thêm code để lưu thông tin vào Firebase
                    showEditDialog = false
                }
            )
        }
    }
}

@Composable
fun InfoSection(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun EditableInfoItem(label: String, value: String, onEditClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        IconButton(onClick = onEditClick) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = "Chỉnh sửa $label",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInfoDialog(
    title: String,
    initialValue: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var editedValue by remember { mutableStateOf(initialValue) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = editedValue,
                    onValueChange = { editedValue = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = false,
                    maxLines = 3
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Hủy")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { onConfirm(editedValue) }) {
                        Text("Lưu")
                    }
                }
            }
        }
    }
}
