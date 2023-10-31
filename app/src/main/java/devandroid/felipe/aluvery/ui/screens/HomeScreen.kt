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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState
import devandroid.felipe.aluvery.ui.components.CardProductItem
import devandroid.felipe.aluvery.ui.components.ProductsSection
import devandroid.felipe.aluvery.ui.components.SearchTextField
import devandroid.felipe.aluvery.ui.components.ShopSection
import devandroid.felipe.aluvery.ui.theme.AluveryTheme
import devandroid.felipe.aluvery.ui.viewmodels.HomeScreenViewModel

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
fun HomeScreen(viewModel: HomeScreenViewModel) {

    val uiState by viewModel.uiState.collectAsState()
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