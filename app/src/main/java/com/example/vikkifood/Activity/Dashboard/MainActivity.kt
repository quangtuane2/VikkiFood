package com.example.vikkifood.Activity.Dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.vikkifood.Activity.BaseActivity
//import com.example.vikkifood.Activity.Splash.MyAppNavigaiton
import com.example.vikkifood.Domain.BannerModel
import com.example.vikkifood.Domain.CategoryModel
import com.example.vikkifood.ViewModel.MainViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val viewModel = MainViewModel()

    val banners =  remember { mutableStateListOf<BannerModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }

    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    Scaffold(bottomBar = {MyBottomBar()}
        , scaffoldState = scaffoldState
    ) {
        paddingValues ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
        ) {

            item {
                TopBar()
            }
            item {
                Banner(banners, showBannerLoading)
            }
            item {
                Search()
            }
            item {
                CategorySection(categories, showCategoryLoading)
            }
        }
    }

    // NavHost
//    NavHost(navController = navController, startDestination = "home") {
//        composable("home") {
//            Scaffold(
//                bottomBar = { MyBottomBar() },
//                scaffoldState = scaffoldState
//            ) { paddingValues ->
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(paddingValues)
//                ) {
//                    item { TopBar(navController) }
//                    item { Banner(banners, showBannerLoading) }
//                    item { Search() }
//                    item { CategorySection(categories, showCategoryLoading) }
//                }
//            }
//        }
//
//        composable("notification") {
//            NotificationScreen()
//        }
//    }
}




