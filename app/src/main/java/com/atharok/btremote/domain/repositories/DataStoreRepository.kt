package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.settings.AppearanceSettings
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.entities.settings.ThemeEntity
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    // ---- Appearance ----

    val appearanceSettingsFlow: Flow<AppearanceSettings>

    suspend fun saveTheme(themeEntity: ThemeEntity)

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean)

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean)

    suspend fun saveUseFullScreen(useFullScreen: Boolean)

    // ---- Remote ----

    val remoteSettingsFlow: Flow<RemoteSettings>

    suspend fun saveMouseSpeed(mouseSpeed: Float)

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean)

    suspend fun saveUseGyroscope(useGyroscope: Boolean)

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage)

    suspend fun saveMustClearInputField(mustClearInputField: Boolean)

    suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean)

    suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean)

    suspend fun saveUseMinimalistRemote(useMinimalistRemote: Boolean)

    suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity)

    suspend fun saveUseEnterForSelection(useEnterForSelection: Boolean)

    suspend fun saveUseMouseNavigationByDefault(useMouseNavigationByDefault: Boolean)

    // ---- Others ----

    fun getFavoriteDevices(): Flow<List<String>>
    suspend fun saveFavoriteDevices(macAddresses: List<String>)

    fun getAutoConnectDeviceAddressFlow(): Flow<String>
    suspend fun saveAutoConnectDeviceAddress(macAddress: String)

    fun hideBluetoothActivationButton(): Flow<Boolean>
    suspend fun saveHideBluetoothActivationButton(hide: Boolean)
}