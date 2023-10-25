package devandroid.felipe.aluvery.stateholders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.sampledata.shopModelList

class HomeScreenUiState(sections: Map<String, List<ProductModel>>) {

    private val sections = sections
    private val listShop = shopModelList
    var textValue by mutableStateOf("")

    val productsFiltered
        get() =
            if (textValue.isNotBlank()) {
                sampleProducts.filter {
                    it.name.contains(
                        textValue,
                        false
                    ) || it.description?.contains(
                        textValue,
                        false
                    ) ?: false
                }
            } else {
                emptyList()
            }

    fun isShowSections(textValue: String): Boolean = textValue.isNotBlank()

    fun getAllSections() : List<Pair<String, List<ProductModel>>> = sections.toList()

    fun getListShop(): List<ShopModel> = listShop
}