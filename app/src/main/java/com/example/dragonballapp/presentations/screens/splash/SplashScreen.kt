package com.example.dragonballapp.presentations.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.dragonballapp.R
import com.example.dragonballapp.ui.theme.DarkRed
import com.example.dragonballapp.ui.theme.Yellow

@Composable
fun SplashScreen(navHostController: NavHostController, pulseFraction: Float = 1.2f) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Splash(scale = scale)
}


@Composable
fun Splash(scale: Float) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.scale(scale),
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
                modifier = Modifier.scale(scale),
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen"
            )
        }

    }

}


@Preview
@Composable
fun PreviewSplashScreen() {
    Splash(scale = 1f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkSplashScreen() {
    Splash(scale = 1f)


}