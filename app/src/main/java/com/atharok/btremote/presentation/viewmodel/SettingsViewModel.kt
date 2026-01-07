package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.settings.AppearanceSettings
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.entities.settings.ThemeEntity
import com.atharok.btremote.domain.usecases.SettingsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val useCase: SettingsUseCase
): ViewModel() {

    // ---- Appearance ----

    val appearanceSettingsFlow: Flow<AppearanceSettings> = useCase.appearanceSettingsFlow

    fun changeTheme(newTheme: ThemeEntity) = viewModelScope.launch {
        useCase.saveTheme(newTheme)
    }

    fun setUseDynamicColors(useDynamicColors: Boolean) = viewModelScope.launch {
        useCase.saveUseDynamicColors(useDynamicColors)
    }

    fun setUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) = viewModelScope.launch {
        useCase.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    fun saveUseFullScreen(useFullScreen: Boolean) = viewModelScope.launch {
        useCase.saveUseFullScreen(useFullScreen)
    }

    // ---- Remote ----

    val remoteSettingsFlow: Flow<RemoteSettings> = useCase.remoteSettingsFlow

    fun saveMouseSpeed(mouseSpeed: Float) = viewModelScope.launch {
        useCase.saveMouseSpeed(mouseSpeed)
    }

    fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) = viewModelScope.launch {
        useCase.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    fun saveUseGyroscope(useGyroscope: Boolean) = viewModelScope.launch {
        useCase.saveUseGyroscope(useGyroscope)
    }

    fun changeKeyboardLanguage(language: KeyboardLanguage) = viewModelScope.launch {
        useCase.saveKeyboardLanguage(language)
    }

    fun saveMustClearInputField(mustClearInputField: Boolean) = viewModelScope.launch {
        useCase.saveMustClearInputField(mustClearInputField)
    }

    fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) = viewModelScope.launch {
        useCase.saveUseAdvancedKeyboard(useAdvancedKeyboard)
    }

    fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean) = viewModelScope.launch {
        useCase.saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated)
    }

    fun saveUseMinimalistRemote(useMinimalistRemote: Boolean) = viewModelScope.launch {
        useCase.saveUseMinimalistRemote(useMinimalistRemote)
    }

    fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity) = viewModelScope.launch {
        useCase.saveRemoteNavigation(remoteNavigationEntity)
    }

    fun saveUseEnterForSelection(useEnterForSelection: Boolean) = viewModelScope.launch {
        useCase.saveUseEnterForSelection(useEnterForSelection)
    }

    fun saveUseMouseNavigationByDefault(useMouseNavigationByDefault: Boolean) = viewModelScope.launch {
        useCase.saveUseMouseNavigationByDefault(useMouseNavigationByDefault)
    }

    // ---- Others ----

    val hideBluetoothActivationButtonFlow: Flow<Boolean> get() = useCase.hideBluetoothActivationButton()
    fun saveHideBluetoothActivationButton(hide: Boolean) = viewModelScope.launch {
        useCase.saveHideBluetoothActivationButton(hide)
    }
}