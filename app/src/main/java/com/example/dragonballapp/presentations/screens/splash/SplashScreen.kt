package com.example.dragonballapp.presentations.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.dragonballapp.R
import com.example.dragonballapp.ui.theme.DarkRed
import com.example.dragonballapp.ui.theme.Yellow

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Splash()
}


@Composable
fun Splash() {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen"
            )
        }

    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(DarkRed, Yellow)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen"
            )
        }

    }

}


@Preview
@Composable
fun PreviewSplashScreen() {
    Splash()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkSplashScreen() {
     Splash()


}