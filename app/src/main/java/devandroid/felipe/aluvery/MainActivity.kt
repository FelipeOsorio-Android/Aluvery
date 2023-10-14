package devandroid.felipe.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import devandroid.felipe.aluvery.ui.components.ProductsSection
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductsSection()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    AluveryTheme {
        Surface {
            ProductsSection()
        }
    }
}


