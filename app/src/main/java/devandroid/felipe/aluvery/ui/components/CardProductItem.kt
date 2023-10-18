package devandroid.felipe.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.extensions.toBrazilCurrency
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.sampledata.sampleProducts
import devandroid.felipe.aluvery.ui.theme.Indigo400Light
import java.math.BigDecimal

@Composable
fun CardProductItem(product: ProductModel, modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = 4.dp
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = Indigo400Light)
                    .padding(16.dp)
            ) {
                Text(text = product.name)
                Text(text = product.price.toBrazilCurrency())
            }
            product.description?.let {
                Text(text = it, Modifier.padding(16.dp))
            }
        }

    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    CardProductItem(product = ProductModel(
        name = "Teste",
        price = BigDecimal(14.99),
        description = LoremIpsum(20).values.first()
    )
    )
}