package devandroid.felipe.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState
import devandroid.felipe.aluvery.ui.components.CardProductItem
import devandroid.felipe.aluvery.ui.components.ProductsSection
import devandroid.felipe.aluvery.ui.components.SearchTextField
import devandroid.felipe.aluvery.ui.components.ShopSection
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(uiState: HomeScreenUiState = HomeScreenUiState()) {

    val filterProducts = uiState.productsFiltered
    val sections = uiState.sections


    Column(
        Modifier.fillMaxSize()
    ) {
        SearchTextField(searchText = uiState.searchText, onSearchChange = uiState.onSearchChange)

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            when(uiState.isShowSections(uiState.searchText)) {

                true -> {
                    items(filterProducts) {
                        CardProductItem(product = it, Modifier.padding(horizontal = 16.dp))
                    }
                }

                else -> {
                    items(sections.toList()) {
                        ProductsSection(title = it.first, listProducts = it.second)
                    }
                    item {
                        ShopSection(listShop = uiState.getListShop())
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(daoProductsList: List<ProductModel>) {
    val sections = mapOf(
        "Todos os Produtos" to daoProductsList,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var textValue by remember { mutableStateOf("") }

    fun filterProducts() = { product: ProductModel ->
        product.name.contains(
            textValue,
            true
        ) || product.description?.contains(
            textValue,
            true
        ) ?: false

    }

    val productsFiltered = remember(textValue, daoProductsList) {
        if (textValue.isNotBlank()) {
            sampleProducts.filter(filterProducts()) +
                    daoProductsList.filter(filterProducts())
        } else {
            emptyList()
        }
    }

    val uiState = remember(daoProductsList, textValue) {
        HomeScreenUiState(
            sections = sections,
            productsFiltered = productsFiltered,
            searchText = textValue,
            onSearchChange = {
                textValue = it
            }
        )
    }
    HomeScreen(uiState)
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState())
        }
    }
}