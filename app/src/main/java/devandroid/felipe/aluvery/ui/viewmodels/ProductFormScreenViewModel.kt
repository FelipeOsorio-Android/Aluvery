package devandroid.felipe.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.model.ProductModel
import devandroid.felipe.aluvery.stateholders.ProductFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.util.regex.Pattern

class ProductFormScreenViewModel : ViewModel() {

    private val dao by lazy { ProductDao() }

    private val _uiState: MutableStateFlow<ProductFormScreenUiState> =
        MutableStateFlow(ProductFormScreenUiState())

    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onChangeValue = { field, newValue ->
                    when (field) {
                        "url" -> {
                            _uiState.value = _uiState.value.copy(
                                textUrl = newValue
                            )
                        }

                        "name" -> {
                            _uiState.value = _uiState.value.copy(
                                name = newValue
                            )
                        }

                        "price" -> {
                            _uiState.value = _uiState.value.copy(
                                price = newValue
                            )
                        }

                        "description" -> {
                            _uiState.value = _uiState.value.copy(
                                description = newValue
                            )
                        }
                    }
                    _uiState.value = _uiState.value.copy(
                        errorFieldValue = _uiState.value.price.contains('-')
                                || _uiState.value.price.contains(',')
                    )
                    _uiState.value = _uiState.value.copy(
                        buttonEnabled = _uiState.value.name.isNotBlank()
                                && _uiState.value.price.isNotBlank()
                                && Pattern.matches(
                            "^\\d{1,3}[+.]\\d{1,2}\$",
                            _uiState.value.price
                        )
                    )
                },

                onCleanField = {
                    when (it) {
                        "url" -> {
                            _uiState.value = _uiState.value.copy(
                                textUrl = ""
                            )
                        }

                        "name" -> {
                            _uiState.value = _uiState.value.copy(
                                name = ""
                            )
                        }

                        "price" -> {
                            _uiState.value = _uiState.value.copy(
                                price = ""
                            )
                        }
                    }
                }
            )
        }
    }

    fun save() {
        _uiState.value.run {
            val product = ProductModel(
                name = name,
                price = BigDecimal(price),
                image = textUrl,
                description = description
            )

            dao.save(product)
        }
    }
}