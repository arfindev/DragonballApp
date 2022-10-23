package com.example.dragonballapp.presentations.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.dragonballapp.navigation.Screens
import com.example.dragonballapp.presentations.screens.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClick = {
                    navController.navigate(Screens.Search.route)
                }
            )
        },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding)
                .fillMaxSize()) {
                ListContent(heroes = allHeroes, navController = navController)

            }
        })


}