package com.example.dragonballapp.presentations.Details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.use_cases.UseCases
import com.example.dragonballapp.util.Constant.DETAIL_SCREEN_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAIL_SCREEN_KEY)
            _selectedHero.value = heroId?.let {
                useCases.selectedHeroUseCase(heroId = heroId)
            }
            _selectedHero.value?.id?.let {
                Log.d("heroname", listOf(it).toString())
            }
        }
    }

}