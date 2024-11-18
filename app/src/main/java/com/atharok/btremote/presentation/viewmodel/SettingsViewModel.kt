package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.usecases.SettingsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val useCase: SettingsUseCase
): ViewModel() {

    val theme: Flow<ThemeEntity> get() = useCase.getTheme()
    fun changeTheme(newTheme: ThemeEntity) = viewModelScope.launch {
        useCase.saveTheme(newTheme)
    }

    val useDynamicColors: Flow<Boolean> get() = useCase.useDynamicColors()
    fun setUseDynamicColors(useDynamicColors: Boolean) = viewModelScope.launch {
        useCase.saveUseDynamicColors(useDynamicColors)
    }

    val useBlackColorForDarkTheme: Flow<Boolean> get() = useCase.useBlackColorForDarkTheme()
    fun setUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) = viewModelScope.launch {
        useCase.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    val mouseSpeed: Flow<Float> get() = useCase.getMouseSpeed()
    fun saveMouseSpeed(mouseSpeed: Float) = viewModelScope.launch {
        useCase.saveMouseSpeed(mouseSpeed)
    }

    val shouldInvertMouseScrollingDirection: Flow<Boolean> get() = useCase.shouldInvertMouseScrollingDirection()
    fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) = viewModelScope.launch {
        useCase.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    val useGyroscope: Flow<Boolean> get() = useCase.useGyroscope()
    fun saveUseGyroscope(useGyroscope: Boolean) = viewModelScope.launch {
        useCase.saveUseGyroscope(useGyroscope)
    }

    val keyboardLanguage: Flow<KeyboardLanguage> get() = useCase.getKeyboardLanguage()
    fun changeKeyboardLanguage(language: KeyboardLanguage) = viewModelScope.launch {
        useCase.saveKeyboardLanguage(language)
    }

    val mustClearInputField: Flow<Boolean> get() = useCase.mustClearInputField()
    fun saveMustClearInputField(mustClearInputField: Boolean) = viewModelScope.launch {
        useCase.saveMustClearInputField(mustClearInputField)
    }

    val useAdvancedKeyboard: Flow<Boolean> get() = useCase.useAdvancedKeyboard()
    fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) = viewModelScope.launch {
        useCase.saveUseAdvancedKeyboard(useAdvancedKeyboard)
    }

    val useMinimalistRemote: Flow<Boolean> get() = useCase.useMinimalistRemote()
    fun saveUseMinimalistRemote(useMinimalistRemote: Boolean) = viewModelScope.launch {
        useCase.saveUseMinimalistRemote(useMinimalistRemote)
    }
}