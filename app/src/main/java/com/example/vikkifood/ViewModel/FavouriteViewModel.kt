package com.example.vikkifood.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.Repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavouriteRepository(application.applicationContext)

    private val _favouriteItems = MutableLiveData<List<FoodModel>>()
    val favouriteItems: LiveData<List<FoodModel>> = _favouriteItems

    init {
        loadFavouriteItems()
    }

    private fun loadFavouriteItems() {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) {
                repository.getFavouriteItems()
            }
            _favouriteItems.value = items
        }
    }

    fun addToFavourite(item: FoodModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addToFavourite(item)
            }
            // Cập nhật lại danh sách sau khi thêm
            loadFavouriteItems()
        }
    }

    fun removeFromFavourite(item: FoodModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.removeFromFavourite(item)
            }
            // Cập nhật lại danh sách sau khi xóa
            loadFavouriteItems()
        }
    }

    fun removeFromFavouriteById(itemId: Int) {
        val item = _favouriteItems.value?.find { it.Id == itemId }
        item?.let { removeFromFavourite(it) }
    }

    fun isFavourite(itemId: Int): Boolean {
        return repository.isFavouriteById(itemId)
    }

    fun toggleFavourite(item: FoodModel): Boolean {
        val result = repository.toggleFavourite(item)
        loadFavouriteItems() // Cập nhật danh sách sau khi thay đổi
        return result
    }

    fun getFavouriteCount(): Int {
        return repository.getFavouriteCount()
    }

    fun clearAllFavourites() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.clearAllFavourites()
            }
            loadFavouriteItems()
        }
    }
}
