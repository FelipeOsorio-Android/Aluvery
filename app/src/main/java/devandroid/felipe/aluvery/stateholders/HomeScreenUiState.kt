package devandroid.felipe.aluvery.stateholders

import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.sampledata.shopModelList

class HomeScreenUiState(
    val sections: Map<String, List<ProductModel>> = emptyMap(),
    val productsFiltered: List<ProductModel> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {


    private val listShop = shopModelList


    fun isShowSections(textValue: String): Boolean = textValue.isNotBlank()

    fun getListShop(): List<ShopModel> = listShop

}