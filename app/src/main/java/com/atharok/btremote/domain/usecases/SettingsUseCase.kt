package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsUseCase(private val repository: SettingsRepository) {

    fun getTheme(): Flow<ThemeEntity> = repository.getTheme()
    suspend fun saveTheme(themeEntity: ThemeEntity) {
        repository.saveTheme(themeEntity)
    }

    fun useDynamicColors(): Flow<Boolean> = repository.useDynamicColors()
    suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        repository.saveUseDynamicColors(useDynamicColors)
    }

    fun useBlackColorForDarkTheme(): Flow<Boolean> = repository.useBlackColorForDarkTheme()
    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        repository.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    fun getMouseSpeed(): Flow<Float> = repository.getMouseSpeed()
    suspend fun saveMouseSpeed(mouseSpeed: Float) {
        repository.saveMouseSpeed(mouseSpeed)
    }

    fun shouldInvertMouseScrollingDirection(): Flow<Boolean> = repository.shouldInvertMouseScrollingDirection()
    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        repository.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    fun useGyroscope(): Flow<Boolean> = repository.useGyroscope()
    suspend fun saveUseGyroscope(useGyroscope: Boolean) {
        repository.saveUseGyroscope(useGyroscope)
    }

    fun getKeyboardLanguage(): Flow<KeyboardLanguage> = repository.getKeyboardLanguage()
    suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        repository.saveKeyboardLanguage(language)
    }

    fun mustClearInputField(): Flow<Boolean> = repository.mustClearInputField()
    suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        repository.saveMustClearInputField(mustClearInputField)
    }

    fun useAdvancedKeyboard(): Flow<Boolean> = repository.useAdvancedKeyboard()
    suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) {
        repository.saveUseAdvancedKeyboard(useAdvancedKeyboard)
    }

    fun useMinimalistRemote(): Flow<Boolean> = repository.useMinimalistRemote()
    suspend fun saveUseMinimalistRemote(useMinimalistRemote: Boolean) {
        repository.saveUseMinimalistRemote(useMinimalistRemote)
    }
}