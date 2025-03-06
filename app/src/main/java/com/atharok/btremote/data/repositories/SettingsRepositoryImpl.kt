package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.domain.entity.RemoteNavigationEntity
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val settingsDataStore: SettingsDataStore
): SettingsRepository {

    override fun getTheme(): Flow<ThemeEntity> = settingsDataStore.themeFlow
    override suspend fun saveTheme(themeEntity: ThemeEntity) {
        settingsDataStore.saveTheme(themeEntity)
    }

    override fun useDynamicColors(): Flow<Boolean> = settingsDataStore.useDynamicColorsFlow
    override suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        settingsDataStore.saveUseDynamicColors(useDynamicColors)
    }

    override fun useBlackColorForDarkTheme(): Flow<Boolean> = settingsDataStore.useBlackColorForDarkThemeFlow
    override suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        settingsDataStore.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    override fun useFullScreen(): Flow<Boolean> = settingsDataStore.useFullScreenFlow

    override suspend fun saveUseFullScreen(useFullScreen: Boolean) {
        settingsDataStore.saveUseFullScreen(useFullScreen)
    }

    override fun getMouseSpeed(): Flow<Float> = settingsDataStore.mouseSpeed
    override suspend fun saveMouseSpeed(mouseSpeed: Float) {
        settingsDataStore.saveMouseSpeed(mouseSpeed)
    }

    override fun shouldInvertMouseScrollingDirection(): Flow<Boolean> = settingsDataStore.shouldInvertMouseScrollingDirection
    override suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        settingsDataStore.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    override fun useGyroscope(): Flow<Boolean> = settingsDataStore.useGyroscope
    override suspend fun saveUseGyroscope(useGyroscope: Boolean) {
        settingsDataStore.saveUseGyroscope(useGyroscope)
    }

    override fun getKeyboardLanguage(): Flow<KeyboardLanguage> = settingsDataStore.keyboardLanguageFlow
    override suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        settingsDataStore.saveKeyboardLanguage(language)
    }

    override fun mustClearInputField(): Flow<Boolean> = settingsDataStore.mustClearInputFieldFlow
    override suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        settingsDataStore.saveMustClearInputField(mustClearInputField)
    }

    override fun useAdvancedKeyboard(): Flow<Boolean> = settingsDataStore.useAdvancedKeyboardFlow
    override suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) {
        settingsDataStore.saveUseAdvancedKeyboard(useAdvancedKeyboard)
    }

    override fun useAdvancedKeyboardIntegrated(): Flow<Boolean> = settingsDataStore.useAdvancedKeyboardIntegratedFlow
    override suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean) {
        settingsDataStore.saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated)
    }

    override fun useMinimalistRemote(): Flow<Boolean> = settingsDataStore.useMinimalistRemoteFlow
    override suspend fun saveUseMinimalistRemote(useMinimalistRemote: Boolean) {
        settingsDataStore.saveUseMinimalistRemote(useMinimalistRemote)
    }

    override fun getRemoteNavigation(): Flow<RemoteNavigationEntity> = settingsDataStore.remoteNavigationFlow

    override suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity) {
        settingsDataStore.saveRemoteNavigation(remoteNavigationEntity)
    }
}