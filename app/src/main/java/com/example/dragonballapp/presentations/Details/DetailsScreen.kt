package com.example.dragonballapp.presentations.Details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.dragonballapp.presentations.Details.components.DetailsContent
import com.example.dragonballapp.util.Constant.BASE_URL
import com.example.dragonballapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.example.dragonballapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
    navController: NavHostController

) {

    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()
    val colorPalette by detailsScreenViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsScreenViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsScreenViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is DetailsScreenViewModel.UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsScreenViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }

        }
    }

}