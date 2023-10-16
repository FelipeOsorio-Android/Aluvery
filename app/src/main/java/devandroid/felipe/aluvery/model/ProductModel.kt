package devandroid.felipe.aluvery.model

import androidx.annotation.DrawableRes
import java.math.BigDecimal

class ProductModel(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null
) {
}