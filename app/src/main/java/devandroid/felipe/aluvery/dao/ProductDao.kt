package devandroid.felipe.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import devandroid.felipe.aluvery.model.ProductModel

class ProductDao {
    companion object {
        private val products = mutableStateListOf<ProductModel>()
    }

    fun products() = products.toList()

    fun save(product: ProductModel) {
        products.add(product)
    }
}