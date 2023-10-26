package devandroid.felipe.aluvery.stateholders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.sampledata.shopModelList

class HomeScreenUiState(
    val sections: Map<String, List<ProductModel>> = emptyMap(),
    private val daoProductList: List<ProductModel> = emptyList(),
    searchText: String = ""
) {


    private val listShop = shopModelList
    var textValue by mutableStateOf(searchText)
        private set

    val onSearchChange: (String) -> Unit = { searchedText ->
        textValue = searchedText
    }

    val productsFiltered
        get() = if (textValue.isNotBlank()) {
            sampleProducts.filter(filterProducts()) +
                    daoProductList.filter(filterProducts())
        } else {
            emptyList()
        }

    fun isShowSections(textValue: String): Boolean = textValue.isNotBlank()

    fun getListShop(): List<ShopModel> = listShop

    private fun filterProducts() = { product: ProductModel ->
        product.name.contains(
            textValue,
            true
        ) || product.description?.contains(
            textValue,
            true
        ) ?: false

    }

}