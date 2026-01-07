package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.settings.AppearanceSettings
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.entities.settings.ThemeEntity
import com.atharok.btremote.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreRepositoryImpl(
    private val settingsDataStore: SettingsDataStore
): DataStoreRepository {

    // ---- Appearance ----

    override val appearanceSettingsFlow: Flow<AppearanceSettings>
        get() = settingsDataStore.appearanceSettingsFlow

    override suspend fun saveTheme(themeEntity: ThemeEntity) {
        settingsDataStore.saveTheme(themeEntity)
    }

    override suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        settingsDataStore.saveUseDynamicColors(useDynamicColors)
    }

    override suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        settingsDataStore.saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme)
    }

    override suspend fun saveUseFullScreen(useFullScreen: Boolean) {
        settingsDataStore.saveUseFullScreen(useFullScreen)
    }

    // ---- Remote ----

    override val remoteSettingsFlow: Flow<RemoteSettings>
        get() = settingsDataStore.remoteSettingsFlow

    override suspend fun saveMouseSpeed(mouseSpeed: Float) {
        settingsDataStore.saveMouseSpeed(mouseSpeed)
    }

    override suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        settingsDataStore.saveInvertMouseScrollingDirection(invertScrollingDirection)
    }

    override suspend fun saveUseGyroscope(useGyroscope: Boolean) {
        settingsDataStore.saveUseGyroscope(useGyroscope)
    }

    override suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        settingsDataStore.saveKeyboardLanguage(language)
    }

    override suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        settingsDataStore.saveMustClearInputField(mustClearInputField)
    }

    override suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) {
        settingsDataStore.saveUseAdvancedKeyboard(useAdvancedKeyboard)
    }

    override suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean) {
        settingsDataStore.saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated)
    }

    override suspend fun saveUseMinimalistRemote(useMinimalistRemote: Boolean) {
        settingsDataStore.saveUseMinimalistRemote(useMinimalistRemote)
    }

    override suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity) {
        settingsDataStore.saveRemoteNavigation(remoteNavigationEntity)
    }

    override suspend fun saveUseEnterForSelection(useEnterForSelection: Boolean) {
        settingsDataStore.saveUseEnterForSelection(useEnterForSelection)
    }

    override suspend fun saveUseMouseNavigationByDefault(useMouseNavigationByDefault: Boolean) {
        settingsDataStore.saveUseMouseNavigationByDefault(useMouseNavigationByDefault)
    }

    // ---- Others ----

    override fun getFavoriteDevices(): Flow<List<String>> = settingsDataStore.favoriteDevicesFlow
    override suspend fun saveFavoriteDevices(macAddresses: List<String>) {
        settingsDataStore.saveFavoriteDevices(macAddresses)
    }

    override fun getAutoConnectDeviceAddressFlow(): Flow<String> = settingsDataStore.autoConnectDeviceAddressFlow
    override suspend fun saveAutoConnectDeviceAddress(macAddress: String) {
        settingsDataStore.saveAutoConnectDeviceAddress(macAddress)
    }

    override fun hideBluetoothActivationButton(): Flow<Boolean> = settingsDataStore.hideBluetoothActivationButtonFlow
    override suspend fun saveHideBluetoothActivationButton(hide: Boolean) {
        settingsDataStore.saveHideBluetoothActivationButton(hide)
    }
}