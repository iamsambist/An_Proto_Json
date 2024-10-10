package com.sunaa.callmedady.ui.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunaa.callmedady.dadyrepo.DataDadyRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GameError(
    var errorStatus: Boolean = false,
    var errorMessage: String = "NO Errors"
)

class MainViewModel : ViewModel() {
    var dadyRepo: DataDadyRepo = DataDadyRepo()

    var dadyChoices = mutableListOf<Any>()

    private val _screenState = MutableStateFlow(0)
    val screenState : StateFlow<Int> = _screenState.asStateFlow()

    private val _gameState = MutableStateFlow(1)
    val gameState: StateFlow<Int> = _gameState.asStateFlow()

    private val _gameEnd = MutableStateFlow(false)
    val gameEnd :StateFlow<Boolean> = _gameEnd.asStateFlow()

    private val _error = MutableStateFlow(GameError())
    val error: StateFlow<GameError> = _error.asStateFlow()

    private val _seletedRadioButton = MutableStateFlow(0)
    val selectedRadioButton : StateFlow<Int> = _seletedRadioButton.asStateFlow()
    // if == 1  yes selected if == 2 no selected

    private val _qustionOneText = MutableStateFlow("")
    val questionOneText: StateFlow<String> = _qustionOneText.asStateFlow()

    private val _questionTwoText = MutableStateFlow("")
    val questiontwoText: StateFlow<String> = _questionTwoText.asStateFlow()

    fun updateQuestionsTextField(newValue: String) {
        if (_gameState.value == 1) {
            _qustionOneText.value = newValue
        } else if (_gameState.value == 2) {
            _questionTwoText.value = newValue
        }

    }

    fun performRightSwipe() {
        if (_gameState.value == 1) {
            if (dadyRepo.containsProfession(_qustionOneText.value.uppercase().trim())) {
                _gameState.value += 1
                dadyChoices.add(_qustionOneText.value)
                _error.update { gameError ->
                    gameError.copy(
                        errorStatus = false
                    )
                }
            } else {
                _error.update { gameError ->
                    gameError.copy(
                        errorStatus = true,
                        errorMessage = "NO SUCH PROFESSION EXIST"
                    )
                }
            }
        } else if (_gameState.value == 2) {
            if (questiontwoText.value.trim().isEmpty()) {
                _error.update { gameError: GameError ->
                    gameError.copy(
                        errorStatus = true,
                        errorMessage = "PROVIDE THE CERTAIN INFORMATION HERE"
                    )
                }
            } else {
                _gameState.value += 1
                dadyChoices.add(questiontwoText.value)
                _error.update { gameError ->
                    gameError.copy(
                        errorStatus = false
                    )
                }
            }
        } else{
            if(_seletedRadioButton.value == 0 || _seletedRadioButton.value == 2){
                _error.update {
                    gameError: GameError ->
                    gameError.copy(
                        errorStatus = true,
                        errorMessage = "I ASSUME YOU ARE SAD"
                    )
                }
                dadyChoices.add(false)
                viewModelScope.launch {
                    delay(1000L)
                    _gameEnd.value = true
                    _error.update {
                        gameError ->
                        gameError.copy(
                            errorStatus = false
                        )
                    }
                }
            } else if(_seletedRadioButton.value == 1 ){
                dadyChoices.add(true)
                _gameEnd.value = true
            }


        }
    }

    fun performLeftSwipe() {
        if(_gameState.value == 1){
            _error.update { gameError: GameError ->
                gameError.copy(
                    errorStatus = true,
                    errorMessage = "NOTHING LEFT THERE"
                )
            }
        } else if(_gameState.value == 2 ){
            dadyChoices.removeLast()
            _gameState.value -= 1
        } else{
            dadyChoices.removeLast()
            _gameState.value -= 1
        }
    }

    fun updateRadioSelection(selectedRadioIndex: Int) {
        if(_seletedRadioButton.value == selectedRadioIndex){
            _seletedRadioButton.value =0
        } else{
            _seletedRadioButton.value = selectedRadioIndex
        }
    }

    fun finalClick() {
        _screenState.value = 1
    }

}

