package com.example.dragonballapp.presentations.Details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.dragonballapp.TestScreen
import com.example.dragonballapp.presentations.Details.components.DetailsContent

@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
    navController : NavHostController

) {

    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()

   DetailsContent(navController = navController, selectedHero = selectedHero)

}