package com.example.dragonballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.dragonballapp.navigation.SetupNavGraph
import com.example.dragonballapp.ui.theme.DragonballAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private lateinit var navController: NavHostController
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            DragonballAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)


            }
        }
    }

}

