package com.example.dragonballapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dragonballapp.presentations.screens.home.HomeScreen
import com.example.dragonballapp.presentations.screens.splash.SplashScreen
import com.example.dragonballapp.presentations.screens.welcome.WelcomeScreen
import com.example.dragonballapp.util.Constant.DETAIL_SCREEN_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Home.route) {
            HomeScreen()

        }
        composable(route = Screens.Details.route) {

        }
        composable(route = Screens.Search.route) {

        }
        composable(route = Screens.Splash.route) {
            SplashScreen(navHostController = navController)

        }
        composable(
            route = Screens.Welcome.route, arguments = listOf(navArgument(
                DETAIL_SCREEN_KEY
            ) {
                type = NavType.IntType
            })
        ) {
            WelcomeScreen(navController = navController)

        }

    }

}