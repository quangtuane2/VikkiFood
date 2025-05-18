package com.example.vikkifood.Activity.DetailEachFood

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.vikkifood.Activity.BaseActivity
import com.example.vikkifood.Domain.FoodModel
import com.example.vikkifood.Helper.ManagmentCart
import com.example.vikkifood.ViewModel.FavouriteViewModel

class DetailEachFoodActivity : BaseActivity() {
    private lateinit var item: FoodModel
    private lateinit var managmentCart: ManagmentCart
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = intent.getSerializableExtra("object") as FoodModel
        item.numberInCart = 1
        managmentCart = ManagmentCart(this)

        setContent{
            DetailScreen(
                item = item,
                onBackClick = { finish()},
                onAddToCartClick = {
                    managmentCart.insertItem(item)
                },
                favouriteViewModel = favouriteViewModel
            )
        }
    }
}


@Composable
private fun DetailScreen(
    item: FoodModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    favouriteViewModel: FavouriteViewModel
){
    var numberInCart by remember { mutableStateOf(item.numberInCart) }
    val context = LocalContext.current

    // Kiểm tra xem món ăn có trong danh sách yêu thích không
    var isFavourite by remember { mutableStateOf(favouriteViewModel.isFavourite(item.Id)) }

    ConstraintLayout {
        val (footer, column) = createRefs()
        Column (modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .constrainAs(column){
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
            .padding(bottom = 80.dp)
        ){
            HeaderSection(
                item = item,
                numberInCart = numberInCart,
                onBackClick = onBackClick,
                isFavourite = isFavourite,
                onFavoriteClick = {
                    // Sửa cách xử lý yêu thích
                    val wasAlreadyFavourite = isFavourite
                    isFavourite = favouriteViewModel.toggleFavourite(item)

                    // Hiển thị thông báo dựa trên sự thay đổi trạng thái
                    val message = if (!wasAlreadyFavourite) {
                        "Đã thêm vào yêu thích"
                    } else {
                        "Đã xóa khỏi yêu thích"
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                },
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart
                },
                onDecrement = {
                    if(numberInCart > 1){
                        numberInCart--
                        item.numberInCart = numberInCart
                    }
                }
            )
            DescriptionSection(item.Description)
        }

        FooterSection(
            onAddToCartClick,
            totalPrice = (item.Price*numberInCart),
            Modifier.constrainAs(footer){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
    }
}