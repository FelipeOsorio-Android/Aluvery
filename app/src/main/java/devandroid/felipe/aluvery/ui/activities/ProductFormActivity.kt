package devandroid.felipe.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.ui.screens.ProductFormScreen
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    private val dao by lazy { ProductDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val listProduct by remember {
                        mutableStateOf(dao.products())
                    }

                    if(listProduct.size != dao.products().size) {
                        listProduct.toMutableList().add(dao.products().last())
                        finish()
                    } else {
                        ProductFormScreen(dao)
                    }
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