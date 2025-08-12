package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.ThemeEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getTheme(): Flow<ThemeEntity>
    suspend fun saveTheme(themeEntity: ThemeEntity)

    fun useDynamicColors(): Flow<Boolean>
    suspend fun saveUseDynamicColors(useDynamicColors: Boolean)

    fun useBlackColorForDarkTheme(): Flow<Boolean>
    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean)

    fun useFullScreen(): Flow<Boolean>
    suspend fun saveUseFullScreen(useFullScreen: Boolean)

    fun getMouseSpeed(): Flow<Float>
    suspend fun saveMouseSpeed(mouseSpeed: Float)

    fun shouldInvertMouseScrollingDirection(): Flow<Boolean>
    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean)

    fun useGyroscope(): Flow<Boolean>
    suspend fun saveUseGyroscope(useGyroscope: Boolean)

    fun getKeyboardLanguage(): Flow<KeyboardLanguage>
    suspend fun saveKeyboardLanguage(language: KeyboardLanguage)

    fun mustClearInputField(): Flow<Boolean>
    suspend fun saveMustClearInputField(mustClearInputField: Boolean)

    fun useAdvancedKeyboard(): Flow<Boolean>
    suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean)

    fun useAdvancedKeyboardIntegrated(): Flow<Boolean>
    suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean)

    fun useMinimalistRemote(): Flow<Boolean>
    suspend fun saveUseMinimalistRemote(useMinimalistRemote: Boolean)

    fun getRemoteNavigation(): Flow<RemoteNavigationEntity>
    suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity)

    fun useEnterForSelection(): Flow<Boolean>
    suspend fun saveUseEnterForSelection(useEnterForSelection: Boolean)

    fun getFavoriteDevices(): Flow<List<String>>
    suspend fun saveFavoriteDevices(macAddresses: List<String>)
}