package devandroid.felipe.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import devandroid.felipe.aluvery.ui.screens.ProductFormScreen
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductForm()
                }
            }
        }
    }
}


@Composable
private fun ProductForm() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}