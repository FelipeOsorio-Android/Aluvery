package devandroid.felipe.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao by lazy { ProductDao() }

    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        sections = mapOf(
            "Todos os Produtos" to dao.products(),
            "Promoções" to sampleDrinks + sampleCandies,
            "Doces" to sampleCandies,
            "Bebidas" to sampleDrinks
        ),
        onSearchChange = {
            uiState = uiState.copy(
                searchText = it,
                productsFiltered = productsFiltered(it)
            )
        }
    ))
        private set


    private fun filterProducts(text: String) = { product: ProductModel ->
        product.name.contains(
            text,
            true
        ) || product.description?.contains(
            text,
            true
        ) ?: false

    }

    private fun productsFiltered(text: String): List<ProductModel> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(filterProducts(text)) +
                    dao.products().filter(filterProducts(text))
        } else {
            emptyList()
        }
    }
}