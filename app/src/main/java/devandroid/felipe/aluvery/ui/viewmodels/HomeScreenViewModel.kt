package devandroid.felipe.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao by lazy { ProductDao() }

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())

    val uiState get() = _uiState.asStateFlow()


    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        productsFiltered = productsFiltered(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.products().collect { listProducts ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos os Produtos" to listProducts,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    productsFiltered = productsFiltered(_uiState.value.searchText)
                )

            }
        }

    }


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
                    dao.products().value.filter(filterProducts(text))
        } else {
            emptyList()
        }
    }
}