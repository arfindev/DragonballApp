package com.example.dragonballapp.presentations.Details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.ui.INFO_ICON_SIZE
import com.example.dragonballapp.ui.MIN_SHEET_HEIGHT
import com.example.dragonballapp.ui.SMALL_PADDING
import com.example.dragonballapp.util.Constant.BASE_URL


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(selectedHero = it)
            }
        }
    ) {
        Box {
            selectedHero?.let {
                BackgroundContent(heroImage = it.image, onClosedClicked = {
                    navController.popBackStack()
                })
            }

        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onClosedClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(com.example.dragonballapp.R.drawable.ic_placeholder)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(
                    Alignment.TopStart
                ),
            painter = painter,
            contentDescription = "hero_Image",
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onClosedClicked() }) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )

            }


        }
    }


}

