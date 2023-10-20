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
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.sampledata.sampleSections
import devandroid.felipe.aluvery.sampledata.shopModelList
import devandroid.felipe.aluvery.ui.components.CardProductItem
import devandroid.felipe.aluvery.ui.components.ProductsSection
import devandroid.felipe.aluvery.ui.components.SearchTextField
import devandroid.felipe.aluvery.ui.components.ShopSection
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<ProductModel>>,
    listProducts: List<ProductModel>,
    listShop: List<ShopModel>
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        var textValue by remember { mutableStateOf("") }

        SearchTextField(searchText = textValue, onSearchChange = {
            textValue = it
        })

        val productsFiltered = remember(textValue) {
            when {
                textValue.isNotBlank() -> {
                    listProducts.filter {
                        it.name.contains(
                            textValue,
                            false
                        ) || it.description?.contains(
                            textValue,
                            false
                        ) ?: false
                    }
                }

                else -> {
                    emptyList()
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            when {
                textValue.isNotBlank() -> {
                    items(productsFiltered) {
                        CardProductItem(product = it, Modifier.padding(horizontal = 16.dp))
                    }
                }

                else -> {
                    items(sections.toList()) {
                        ProductsSection(title = it.first, listProducts = it.second)
                    }
                    item {
                        ShopSection(listShop = listShop)
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
            HomeScreen(sampleSections, sampleProducts, shopModelList)
        }
    }
}