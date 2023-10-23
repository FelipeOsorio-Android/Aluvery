package devandroid.felipe.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.model.ProductModel
import java.math.BigDecimal
import java.util.regex.Pattern

@Composable
fun ProductFormScreen(modifier: Modifier = Modifier) {

    var textUrl by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val errorFieldPrice by remember(price) {
        mutableStateOf(Pattern.matches("^[^,-]*\$", price))
    }
    val validatePrice by remember(price) {
        mutableStateOf(Pattern.matches("^\\d{1,3}[+.]\\d{1,3}[+.]\\d{1,2}\$", price))
    }

    val buttonEnabled by remember(name, price) {
        mutableStateOf(name.isNotBlank() && price.isNotBlank() && validatePrice)
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(PaddingValues(vertical = 16.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Criando o produto", fontSize = 28.sp, fontWeight = FontWeight(500))

        when {
            textUrl.isNotBlank() -> {
                AsyncImage(
                    model = textUrl,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder)
                )
            }
        }

        OutlinedTextField(
            value = textUrl,
            onValueChange = { textUrl = it },
            Modifier
                .fillMaxWidth(),
            label = { Text(text = "Url") },
            placeholder = { Text(text = "URL da imagem") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_url),
                    contentDescription = null
                )
            },
            trailingIcon = {
                if(textUrl.isNotBlank()) {
                    IconButton(onClick = { textUrl = "" }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )

        Column {
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
                    if (name.isNotBlank()) {
                        IconButton(onClick = { name = "" }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                )
            )
            Text(text = "Campo Obrigatório*", style = MaterialTheme.typography.caption)
        }

        Column {
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                Modifier
                    .fillMaxWidth(),
                label = { Text(text = "Preço") },
                placeholder = { Text(text = "Preço do produto") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_price),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (price.isNotBlank()) {
                        IconButton(onClick = { price = "" }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                },
                isError = !errorFieldPrice
            )
            if(!errorFieldPrice) {
                Text(
                    text = "Valor Invalido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            } else {
                Text(
                    text = "Campo Obrigatório*",
                    style = MaterialTheme.typography.caption
                )
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = { Text(text = "Descrição") },
            placeholder = { Text(text = "Descrição do produto") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Default,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

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