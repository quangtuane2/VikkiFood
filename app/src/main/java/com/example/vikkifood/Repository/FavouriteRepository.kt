package com.example.vikkifood.Repository

import android.content.Context
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.Helper.TinyDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavouriteRepository(private val context: Context) {
    private val KEY_FAVOURITE = "favourite_items"
    private val tinyDB = TinyDB(context)
    private val gson = Gson()

    /**
     * Lấy danh sách tất cả các món ăn yêu thích
     */
    fun getFavouriteItems(): List<FoodModel> {
        val json = tinyDB.getString(KEY_FAVOURITE)
        if (json.isNullOrEmpty()) {
            return emptyList()
        }

        val type = object : TypeToken<List<FoodModel>>() {}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * Thêm một món ăn vào danh sách yêu thích
     */
    fun addToFavourite(item: FoodModel) {
        val currentList = getFavouriteItems().toMutableList()

        // Kiểm tra xem món ăn đã có trong danh sách chưa
        if (!currentList.any { it.Id == item.Id }) {
            currentList.add(item)
            saveFavouriteItems(currentList)
        }
    }

    /**
     * Xóa một món ăn khỏi danh sách yêu thích
     */
    fun removeFromFavourite(item: FoodModel) {
        val currentList = getFavouriteItems().toMutableList()
        currentList.removeAll { it.Id == item.Id }
        saveFavouriteItems(currentList)
    }

    /**
     * Kiểm tra xem một món ăn có nằm trong danh sách yêu thích không
     */
    fun isFavourite(item: FoodModel): Boolean {
        val currentList = getFavouriteItems()
        return currentList.any { it.Id == item.Id }
    }

    /**
     * Kiểm tra xem một món ăn có nằm trong danh sách yêu thích không dựa trên ID
     */
    fun isFavouriteById(itemId: Int): Boolean {
        val currentList = getFavouriteItems()
        return currentList.any { it.Id == itemId }
    }

    /**
     * Chuyển đổi trạng thái yêu thích của món ăn
     * Nếu món ăn đã yêu thích thì xóa khỏi danh sách, ngược lại thêm vào danh sách
     */
    fun toggleFavourite(item: FoodModel): Boolean {
        return if (isFavourite(item)) {
            removeFromFavourite(item)
            false
        } else {
            addToFavourite(item)
            true
        }
    }

    /**
     * Lưu danh sách món ăn yêu thích vào bộ nhớ
     */
    private fun saveFavouriteItems(items: List<FoodModel>) {
        val json = gson.toJson(items)
        tinyDB.putString(KEY_FAVOURITE, json)
    }

    /**
     * Xóa tất cả món ăn khỏi danh sách yêu thích
     */
    fun clearAllFavourites() {
        tinyDB.remove(KEY_FAVOURITE)
    }

    /**
     * Lấy số lượng món ăn yêu thích
     */
    fun getFavouriteCount(): Int {
        return getFavouriteItems().size
    }
}
