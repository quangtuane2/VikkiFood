package com.example.vikkifood.Activity.favourite

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.vikkifood.Activity.BaseActivity
import com.example.vikkifood.ViewModel.FavouriteViewModel

class FavouriteActivity : BaseActivity() {
    private val viewModel: FavouriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FavouriteScreen(
                onBackClick = { finish() },
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun FavouriteScreen(onBackClick: () -> Unit, viewModel: FavouriteViewModel) {
    val favouriteItems by viewModel.favouriteItems.observeAsState(initial = emptyList())

    Scaffold(
        topBar = { FavouriteTopBar(onBackClick = onBackClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (favouriteItems.isEmpty()) {
                EmptyFavouriteView()
            } else {
                FavouriteItemsList(
                    items = favouriteItems,
                    viewModel = viewModel
                )
            }
        }
    }
}
