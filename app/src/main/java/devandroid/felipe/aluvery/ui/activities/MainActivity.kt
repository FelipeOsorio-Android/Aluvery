package devandroid.felipe.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import devandroid.felipe.aluvery.dao.ProductDao
import devandroid.felipe.aluvery.sampledata.sampleCandies
import devandroid.felipe.aluvery.sampledata.sampleDrinks
import devandroid.felipe.aluvery.stateholders.HomeScreenUiState
import devandroid.felipe.aluvery.ui.screens.HomeScreen
import devandroid.felipe.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    
    private val dao by lazy { ProductDao() }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val sections = mapOf(
                    "Todos os Produtos" to dao.products(),
                    "Promoções" to sampleDrinks + sampleCandies,
                    "Doces" to sampleCandies,
                    "Bebidas" to sampleDrinks
                )
                HomeScreen(HomeScreenUiState(sections), sections)
            }
        }
    }
}

@Composable
private fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            androidx.compose.material.Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { onFabClick() }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    content()
                }
            }
        }
    }
}


