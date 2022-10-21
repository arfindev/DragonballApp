package com.example.dragonballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dragonballapp.navigation.SetupNavGraph
import com.example.dragonballapp.ui.theme.DragonballAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private lateinit var navController: NavHostController
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

