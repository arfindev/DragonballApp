package com.example.dragonballapp.presentations.Details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp.domain.model.Hero
import com.example.dragonballapp.domain.use_cases.UseCases
import com.example.dragonballapp.util.Constant.DETAIL_SCREEN_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _uiEvent  = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent



    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>>  = _colorPalette

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }


    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }


    sealed class UiEvent {
        object GenerateColorPalette : UiEvent()
    }

}