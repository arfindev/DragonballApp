package com.example.dragonballapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.presentations.components.OrderList

@Composable
fun TestScreen(
    hero: Hero?

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (hero != null) {
            OrderList(title = "Hero", items = hero.natureType, textColor = Color.Black)
        }
    }


}