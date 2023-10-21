package devandroid.felipe.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.model.ProductModel
import java.math.BigDecimal
import java.util.regex.Pattern

@Composable
fun ProductFormScreen(modifier: Modifier = Modifier) {

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Criando o produto", fontSize = 28.sp, fontWeight = FontWeight(500))

        var textUrl by remember { mutableStateOf("") }
        OutlinedTextField(
            value = textUrl,
            onValueChange = { textUrl = it },
            Modifier
                .fillMaxWidth(),
            label = { Text(text = "Url") },
            placeholder = { Text(text = "URL da imagem") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_url),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        )

        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            Modifier
                .fillMaxWidth(),
            label = { Text(text = "Nome") },
            placeholder = { Text(text = "Nome do produto") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_product_name),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        )

        var price by remember { mutableStateOf("") }
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            Modifier
                .fillMaxWidth(),
            label = { Text(text = "Preço") },
            placeholder = { Text(text = "Preço do produto") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_price),
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        )

        var description by remember { mutableStateOf("") }
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = { Text(text = "Descrição") },
            placeholder = { Text(text = "Descrição do produto") },
        )

        val validatePrice by remember(price) {
            mutableStateOf(Pattern.matches("^[0-9]+\$", price))
        }

        val buttonEnabled by remember(name, price) {
            mutableStateOf(name.isNotBlank() && price.isNotBlank() && validatePrice)
        }

        Button(
            onClick = {
                ProductModel(
                    name = name,
                    price = BigDecimal(price),
                    image = textUrl,
                    description = description
                )
            },
            Modifier
                .fillMaxWidth(),
            enabled = buttonEnabled
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    ProductFormScreen()
}