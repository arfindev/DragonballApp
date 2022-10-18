package com.example.dragonballapp.presentations.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dragonballapp.R
import com.example.dragonballapp.navigation.Screens
import com.example.dragonballapp.ui.theme.DarkYellow
import com.example.dragonballapp.ui.theme.Yellow

@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {

    val onBoardingCompleted by splashScreenViewModel.onBoardingCompleted.collectAsState()

    val degrees = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navHostController.popBackStack()
        if (onBoardingCompleted) {
            navHostController.navigate(Screens.Home.route)
        } else {
            navHostController.navigate(Screens.Welcome.route)
        }
    }
    Splash(scale = degrees.value)
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
                modifier = Modifier.rotate(scale),
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen"
            )
        }

    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(DarkYellow, Yellow)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(scale),
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