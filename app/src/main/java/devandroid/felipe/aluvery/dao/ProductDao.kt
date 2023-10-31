package devandroid.felipe.aluvery.dao

import devandroid.felipe.aluvery.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    companion object {
        private val products = MutableStateFlow<List<ProductModel>>(emptyList())
    }

    fun products(): StateFlow<List<ProductModel>> = products.asStateFlow()

    fun save(product: ProductModel) {
        products.value = products.value + product
    }
}