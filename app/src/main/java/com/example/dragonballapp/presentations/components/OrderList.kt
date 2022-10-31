package com.example.dragonballapp.presentations.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.FtsOptions.Order
import com.example.dragonballapp.ui.SMALL_PADDING
import com.example.dragonballapp.ui.theme.titleColor

@Composable
fun OrderList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed { index, items ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $items",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }

}


@Composable
@Preview(showBackground = true)
fun OrderedListPreview() {
    OrderList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        textColor = MaterialTheme.colors.titleColor
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun OrderedListDarkPreview() {
    OrderList(
        title = "Family",
        items = listOf("Minato", "Kushina","gokg"),
        textColor = MaterialTheme.colors.titleColor
    )
}