package com.example.dragonballapp.presentations.Details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.dragonballapp.R
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.presentations.components.InfoBox
import com.example.dragonballapp.presentations.components.OrderList
import com.example.dragonballapp.ui.INFO_ICON_SIZE
import com.example.dragonballapp.ui.LARGE_PADDING
import com.example.dragonballapp.ui.MEDIUM_PADDING
import com.example.dragonballapp.ui.theme.titleColor

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "App Logo",
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = "Month",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = "Birthday",
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Month",
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderList(
                title = "Family",
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderList(
                title = "Abilities",
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderList(
                title = "Nature Tye",
                items = selectedHero.natureType,
                textColor = contentColor
            )
        }
    }
}


@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Naruto",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 4.5,
            power = 0,
            month = "Oct",
            day = "1st",
            family = listOf("Minato", "Kushina", "Boruto", "Himawari"),
            abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
            natureType = listOf("Earth", "Wind")
        )
    )
}

