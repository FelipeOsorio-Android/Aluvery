package devandroid.felipe.aluvery.model

import androidx.annotation.DrawableRes
import java.math.BigDecimal

class ProductModel(
    val name: String,
    val price: BigDecimal,
    @DrawableRes val image: Int,
    val description: String? = null
) {
}