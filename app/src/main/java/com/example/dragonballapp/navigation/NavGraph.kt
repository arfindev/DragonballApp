package com.example.dragonballapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.dragonballapp.presentations.Search.SearchScreen
import com.example.dragonballapp.presentations.screens.home.HomeScreen
import com.example.dragonballapp.presentations.screens.splash.SplashScreen
import com.example.dragonballapp.presentations.screens.welcome.WelcomeScreen
import com.example.dragonballapp.util.Constant.DETAIL_SCREEN_KEY

@ExperimentalCoilApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.Welcome.route) {
            WelcomeScreen(navController = navController)


        }
        composable(route = Screens.Search.route) {
            SearchScreen(navController = navController)

        }
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Screens.Details.route, arguments = listOf(navArgument(
                DETAIL_SCREEN_KEY
            ) {
                type = NavType.IntType
            })
        ) {


        }

    }

}