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
import devandroid.felipe.aluvery.stateholders.ProductFormScreenUiState
import java.math.BigDecimal
import java.util.regex.Pattern

@Composable
fun ProductFormScreen(
    modifier: Modifier = Modifier,
    uiState: ProductFormScreenUiState = ProductFormScreenUiState()
) {

    val textUrl = uiState.textUrl
    val name = uiState.name
    val price = uiState.price
    val description = uiState.description
    val errorFieldValue = uiState.errorFieldValue
    val buttonEnabled = uiState.buttonEnabled


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
            onValueChange = { uiState.onChangeValue("url", it) },
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
                if (uiState.isShowCleanButton(textUrl)) {
                    IconButton(onClick = { uiState.onCleanField("url") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )

        Column {
            OutlinedTextField(
                value = name,
                onValueChange = { uiState.onChangeValue("name", it) },
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
                    if (uiState.isShowCleanButton(name)) {
                        IconButton(onClick = { uiState.onCleanField("name") }) {
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
                onValueChange = { uiState.onChangeValue("price", it) },
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
                    if (uiState.isShowCleanButton(price)) {
                        IconButton(onClick = { uiState.onCleanField("price") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                },
                isError = errorFieldValue
            )
            if (errorFieldValue) {
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
            value = description ?: "",
            onValueChange = { uiState.onChangeValue("description", it) },
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
            onClick = { uiState.onSaveClick },
            Modifier
                .fillMaxWidth(),
            enabled = buttonEnabled
        ) {
            Text(text = "Salvar")
        }
    }
}

@Composable
fun ProductFormScreen() {
    var textUrl by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    val errorFieldValue by remember(price) {
        mutableStateOf(price.contains(',') || price.contains('-'))
    }

    val validatePrice by remember(price) {
        mutableStateOf(Pattern.matches("^\\d{1,3}[+.]\\d{1,2}\$", price))
    }

    val buttonEnabled by remember(name, price) {
        mutableStateOf(name.isNotBlank() && price.isNotBlank() && validatePrice)
    }

    val product = remember(textUrl, name, price, description) {
        ProductModel(
            name = name,
            price = if (price.isNotBlank()) BigDecimal(price) else BigDecimal.ZERO,
            image = textUrl,
            description = description
        )
    }


    val uiState = remember(textUrl, name, price, description) {
        ProductFormScreenUiState(
            textUrl = textUrl,
            name = name,
            price = price,
            description = description,
            errorFieldValue = errorFieldValue,
            buttonEnabled = buttonEnabled,
            onChangeValue = { field, newValue ->
                when (field) {
                    "url" -> {
                        textUrl = newValue
                    }

                    "name" -> {
                        name = newValue
                    }

                    "price" -> {
                        price = newValue
                    }

                    "description" -> {
                        description = newValue
                    }
                }
            },
            onCleanField = {
                when(it) {
                    "url" -> {
                        textUrl = ""
                    }

                    "name" -> {
                        name = ""
                    }

                    "price" -> {
                        price = ""
                    }
                }
            },
            onSaveClick = {

            }
        )
    }

    ProductFormScreen(uiState = uiState)
}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    ProductFormScreen()
}