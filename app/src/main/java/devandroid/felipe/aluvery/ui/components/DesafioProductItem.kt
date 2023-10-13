package devandroid.felipe.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import devandroid.felipe.aluvery.R
import devandroid.felipe.aluvery.ui.theme.Purple500
import devandroid.felipe.aluvery.ui.theme.Teal200

@Composable
fun DesafioProductItem() {
    Surface(
        Modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        shadowElevation = 4.dp
    ) {
        Row(
            Modifier
                .height(200.dp)
                .widthIn(250.dp, 300.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Purple500, Teal200
                            )
                        )
                    )
                    .fillMaxHeight()
                    .width(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .size(100.dp)
                        .align(alignment = Alignment.Center)
                        .offset(x = 50.dp)
                        .clip(shape = CircleShape)
                        .border(2.dp, color = Purple500, shape = CircleShape)

                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Column(
                Modifier
                    .padding(16.dp)
                    .align(CenterVertically)
            ) {
                Text(text = LoremIpsum(50).values.first(), maxLines = 4, overflow = TextOverflow.Clip)
                Text(text = LoremIpsum(50).values.first(), maxLines = 2, overflow = TextOverflow.Clip)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DesafioProductItemPreview() {
    DesafioProductItem()
}