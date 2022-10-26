package com.example.dragonballapp.presentations.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dragonballapp.ui.theme.topAppBarBackgroundColor
import com.example.dragonballapp.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(onSearchClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = {
                onSearchClick()

            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")

            }
        })

}


@Preview
@Composable
fun HomeTopBarPreview1() {
    HomeTopBar {

    }

}