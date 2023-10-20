package devandroid.felipe.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.shopModelList

@Composable
fun ShopSection(listShop: List<ShopModel>) {
    Column {
        Text(
            text = "Lojas Parceiras",
            Modifier.padding(start = 16.dp, top = 8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight(500)
        )
        LazyRow(
            Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listShop) {
                ShopItem(shopStore = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopSectionPreview() {
    ShopSection(shopModelList)
}