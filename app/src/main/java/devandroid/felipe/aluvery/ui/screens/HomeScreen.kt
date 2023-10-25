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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState
import devandroid.felipe.aluvery.ui.components.CardProductItem
import devandroid.felipe.aluvery.ui.components.ProductsSection
import devandroid.felipe.aluvery.ui.components.SearchTextField
import devandroid.felipe.aluvery.ui.components.ShopSection
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(state: HomeScreenUiState, section: Map<String, List<ProductModel>>) {

    val uiState = remember { state }
    val filterProducts = remember(uiState.textValue) { uiState.productsFiltered }

    Column(
        Modifier.fillMaxSize()
    ) {
        SearchTextField(searchText = uiState.textValue, onSearchChange = {
            uiState.textValue = it
        })

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            when(uiState.isShowSections(uiState.textValue)) {

                true -> {
                    items(filterProducts) {
                        CardProductItem(product = it, Modifier.padding(horizontal = 16.dp))
                    }
                }

                else -> {
                    items(section.toList()) {
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

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(
                sections = mapOf(
                    "Todos os Produtos" to emptyList(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks
            )
            ), mapOf(
                "Todos os Produtos" to emptyList(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks
            ))
        }
    }
}