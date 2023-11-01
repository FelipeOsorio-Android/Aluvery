package devandroid.felipe.aluvery.stateholders

data class ProductFormScreenUiState(
    val textUrl: String = "",
    val name: String = "",
    val price: String = "",
    val description: String? = null,
    val errorFieldValue: Boolean = false,
    val buttonEnabled: Boolean = false,
    val onChangeValue: (field: String, newValue: String) -> Unit = {_, _ -> },
    val onCleanField: (String) -> Unit ={},
) {

    fun isShowCleanButton(textField: String) = textField.isNotBlank()


}