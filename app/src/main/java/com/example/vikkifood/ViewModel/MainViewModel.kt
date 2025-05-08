package com.example.vikkifood.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vikkifood.Domain.BannerModel
import com.example.vikkifood.Domain.CategoryModel
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository = MainRepository()

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadFiltered(id:String):LiveData<MutableList<FoodModel>>{
        return repository.loadFiltered(id)
    }
}