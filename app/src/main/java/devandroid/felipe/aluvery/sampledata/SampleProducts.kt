package devandroid.felipe.aluvery.sampledata

import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.model.ProductModel
import java.math.BigDecimal

val sampleProducts = listOf(
    ProductModel(
        name = "Burger",
        price = BigDecimal("14.99"),
        image = R.drawable.burger
    ),
    ProductModel(
        name = "Fries",
        price = BigDecimal("19.99"),
        image = R.drawable.fries
    ),
    ProductModel(
        name = "Pizza",
        price = BigDecimal("10.99"),
        image = R.drawable.pizza
    )

)