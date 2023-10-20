package devandroid.felipe.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.model.ShopModel
import devandroid.felipe.aluvery.sampledata.shopModelList

@Composable
fun ShopItem(shopStore: ShopModel) {
    Card(
        Modifier
            .background(Color.White)
            .heightIn(150.dp, 200.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            AsyncImage(
                model = shopStore.image,
                contentDescription = "Icon ${shopStore.name}",
                Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .clip(shape = CircleShape),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Text(text = shopStore.name, fontSize = 16.sp, fontWeight = FontWeight(500))
        }
    }
}

@Preview
@Composable
private fun ShopItemPreview() {
    ShopItem(shopModelList.random())
}