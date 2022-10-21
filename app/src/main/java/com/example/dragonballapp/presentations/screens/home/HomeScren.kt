package com.example.dragonballapp.presentations.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

  Scaffold(topBar = {
      HomeTopBar(onSearchClick = {})
  }){}



}