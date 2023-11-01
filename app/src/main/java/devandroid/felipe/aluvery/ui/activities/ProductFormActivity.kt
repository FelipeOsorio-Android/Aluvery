package devandroid.felipe.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import devandroid.felipe.aluvery.ui.screens.ProductFormScreen
import devandroid.felipe.aluvery.ui.theme.AluveryTheme
import devandroid.felipe.aluvery.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    private val viewModel by viewModels<ProductFormScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(viewModel = viewModel, onSaveClick = { finish() })
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun ProductFormPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}