package com.example.dragonballapp.presentations.screens.common

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.dragonballapp.R
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.ui.NETWORK_ERROR_ICON_HEIGHT
import com.example.dragonballapp.ui.SMALL_PADDING
import com.example.dragonballapp.ui.theme.DarkGray
import com.example.dragonballapp.ui.theme.LightGray
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun EmptyScreen(error: LoadState.Error? = null, heroes: LazyPagingItems<Hero>? = null) {

    var message by remember {
        mutableStateOf("Find Your Favourite Hero")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search)
    }

    if (error != null) {
        message = parseErrorMessage(message = error.toString())
        icon = R.drawable.ic_error
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }
    SwipeRefresh(swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false

        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(NETWORK_ERROR_ICON_HEIGHT),
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )
            Text(
                modifier = Modifier.padding(top = SMALL_PADDING), text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )

        }

    }
}

/*fun parseErrorIcon(message: String): Int {
    return when {
        message.contains("SocketTimeoutException") -> {
            R.drawable.dragon
        }
        message.contains("ConnectException") -> {
            R.drawable.ic_error
        }
        else -> {
            R.drawable.splash_screen
        }
    }
}*/


fun parseErrorMessage(message: String): String {
    Log.d("error message", message)
    return when {
        message.contains("SocketTimeoutException") ->
            "Server Unavailable"
        message.contains("ConnectException") ->
            "Internet Unavailable"
        else ->
            "Unknown Error"
    }
}