package com.atharok.btremote.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.atharok.btremote.common.extensions.dataStore
import com.atharok.btremote.common.utils.DEFAULT_KEYBOARD_LANGUAGE
import com.atharok.btremote.common.utils.DEFAULT_MOUSE_SPEED
import com.atharok.btremote.common.utils.DEFAULT_MUST_CLEAR_INPUT_FIELD
import com.atharok.btremote.common.utils.DEFAULT_REMOTE_NAVIGATION
import com.atharok.btremote.common.utils.DEFAULT_SHOULD_INVERT_MOUSE_SCROLLING_DIRECTION
import com.atharok.btremote.common.utils.DEFAULT_THEME
import com.atharok.btremote.common.utils.USE_MOUSE_NAVIGATION_BY_DEFAULT
import com.atharok.btremote.common.utils.DEFAULT_USE_ADVANCED_KEYBOARD
import com.atharok.btremote.common.utils.DEFAULT_USE_ADVANCED_KEYBOARD_INTEGRATED
import com.atharok.btremote.common.utils.DEFAULT_USE_BLACK_COLOR_FOR_DARK_THEME
import com.atharok.btremote.common.utils.DEFAULT_USE_ENTER_FOR_SELECTION
import com.atharok.btremote.common.utils.DEFAULT_USE_FULL_SCREEN
import com.atharok.btremote.common.utils.DEFAULT_USE_GYROSCOPE
import com.atharok.btremote.common.utils.DEFAULT_USE_MINIMALIST_REMOTE
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.settings.AppearanceSettings
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.entities.settings.ThemeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import java.io.IOException

class SettingsDataStore(private val context: Context) {

    companion object {
        private const val THEME_KEY = "theme_key"
        private const val DYNAMIC_COLORS_KEY = "material_you_key"
        private const val BLACK_COLOR_KEY = "black_color_key"
        private const val FULL_SCREEN_KEY = "full_screen_key"

        private const val MOUSE_SPEED_KEY = "mouse_speed_key"
        private const val INVERT_MOUSE_SCROLLING_DIRECTION_KEY = "invert_mouse_scrolling_direction_key"
        private const val USE_GYROSCOPE_KEY = "use_gyroscope_key"
        private const val KEYBOARD_LANGUAGE = "keyboard_language"
        private const val MUST_CLEAR_INPUT_FIELD_KEY = "must_clear_input_field_key"
        private const val USE_ADVANCED_KEYBOARD_KEY = "use_advanced_keyboard_key"
        private const val USE_ADVANCED_KEYBOARD_INTEGRATED_KEY = "use_advanced_keyboard_integrated_key"
        private const val USE_MINIMALIST_REMOTE_KEY = "use_minimalist_remote_key"
        private const val REMOTE_NAVIGATION_KEY = "remote_navigation_key"
        private const val USE_ENTER_FOR_SELECTION_KEY = "use_enter_for_selection_key"

        private const val FAVORITE_DEVICES_KEY = "favorite_devices_key"
        private const val AUTO_CONNECT_DEVICE_ADDRESS_KEY = "auto_connect_device_address_key"
        private const val HIDE_BLUETOOTH_ACTIVATION_BUTTON_KEY = "hide_bluetooth_activation_button_key"

        private const val USE_MOUSE_NAVIGATION_BY_DEFAULT_KEY = "use_mouse_navigation_by_default_key"
    }

    private val themeKey = stringPreferencesKey(THEME_KEY)
    private val useDynamicColorsKey = booleanPreferencesKey(DYNAMIC_COLORS_KEY)
    private val useBlackColorForDarkThemeKey = booleanPreferencesKey(BLACK_COLOR_KEY)
    private val useFullScreenKey = booleanPreferencesKey(FULL_SCREEN_KEY)
    private val mouseSpeedKey = floatPreferencesKey(MOUSE_SPEED_KEY)
    private val invertMouseScrollingDirectionKey = booleanPreferencesKey(INVERT_MOUSE_SCROLLING_DIRECTION_KEY)
    private val useGyroscopeKey = booleanPreferencesKey(USE_GYROSCOPE_KEY)
    private val keyboardLanguageKey = stringPreferencesKey(KEYBOARD_LANGUAGE)
    private val mustClearInputFieldKey = booleanPreferencesKey(MUST_CLEAR_INPUT_FIELD_KEY)
    private val useAdvancedKeyboardKey = booleanPreferencesKey(USE_ADVANCED_KEYBOARD_KEY)
    private val useAdvancedKeyboardIntegratedKey  = booleanPreferencesKey(USE_ADVANCED_KEYBOARD_INTEGRATED_KEY)
    private val useMinimalistRemoteKey = booleanPreferencesKey(USE_MINIMALIST_REMOTE_KEY)
    private val remoteNavigationKey = stringPreferencesKey(REMOTE_NAVIGATION_KEY)
    private val useEnterForSelectionKey = booleanPreferencesKey(USE_ENTER_FOR_SELECTION_KEY)
    private val favoriteDevicesKey = stringPreferencesKey(FAVORITE_DEVICES_KEY)
    private val autoConnectDeviceAddressKey = stringPreferencesKey(AUTO_CONNECT_DEVICE_ADDRESS_KEY)
    private val hideBluetoothActivationButtonKey = booleanPreferencesKey(HIDE_BLUETOOTH_ACTIVATION_BUTTON_KEY)
    private val useMouseNavigationByDefaultKey = booleanPreferencesKey(USE_MOUSE_NAVIGATION_BY_DEFAULT_KEY)

    private fun Flow<Preferences>.catchException(): Flow<Preferences> = this.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }

    // ---- Appearance Settings ----

    val appearanceSettingsFlow: Flow<AppearanceSettings> = context.dataStore.data
        .catchException()
        .map { preferences ->
            AppearanceSettings(
                theme = try {
                    ThemeEntity.valueOf(preferences[themeKey] ?: DEFAULT_THEME.name)
                } catch (_: IllegalArgumentException) {
                    DEFAULT_THEME
                },
                useDynamicColors = preferences[useDynamicColorsKey] ?: isDynamicColorsAvailable(),
                useBlackColorForDarkTheme = preferences[useBlackColorForDarkThemeKey] ?: DEFAULT_USE_BLACK_COLOR_FOR_DARK_THEME,
                useFullScreen = preferences[useFullScreenKey] ?: DEFAULT_USE_FULL_SCREEN
            )
        }

    suspend fun saveTheme(themeEntity: ThemeEntity) {
        context.dataStore.edit {
            it[themeKey] = themeEntity.name
        }
    }

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        context.dataStore.edit {
            it[useDynamicColorsKey] = if(isDynamicColorsAvailable()) useDynamicColors else false
        }
    }

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        context.dataStore.edit {
            it[useBlackColorForDarkThemeKey] = useBlackColorForDarkTheme
        }
    }

    suspend fun saveUseFullScreen(useFullScreen: Boolean) {
        context.dataStore.edit {
            it[useFullScreenKey] = useFullScreen
        }
    }

    // ---- Remote Settings ----

    val remoteSettingsFlow: Flow<RemoteSettings> = context.dataStore.data
        .catchException()
        .map { preferences ->
            RemoteSettings(
                // ---- Mouse ----
                mouseSpeed = preferences[mouseSpeedKey] ?: DEFAULT_MOUSE_SPEED,
                shouldInvertMouseScrollingDirection = preferences[invertMouseScrollingDirectionKey] ?: DEFAULT_SHOULD_INVERT_MOUSE_SCROLLING_DIRECTION,
                useGyroscope = preferences[useGyroscopeKey] ?: DEFAULT_USE_GYROSCOPE,

                // ---- Keyboard ----
                keyboardLanguage = try {
                    KeyboardLanguage.valueOf(preferences[keyboardLanguageKey] ?: DEFAULT_KEYBOARD_LANGUAGE.name)
                } catch (_: IllegalArgumentException) {
                    DEFAULT_KEYBOARD_LANGUAGE
                },
                mustClearInputField = preferences[mustClearInputFieldKey] ?: DEFAULT_MUST_CLEAR_INPUT_FIELD,
                useAdvancedKeyboard = preferences[useAdvancedKeyboardKey] ?: DEFAULT_USE_ADVANCED_KEYBOARD,
                useAdvancedKeyboardIntegrated = preferences[useAdvancedKeyboardIntegratedKey] ?: DEFAULT_USE_ADVANCED_KEYBOARD_INTEGRATED,

                // ---- Remote ----
                remoteNavigationEntity = try {
                    RemoteNavigationEntity.valueOf(preferences[remoteNavigationKey] ?: DEFAULT_REMOTE_NAVIGATION.name)
                } catch (_: IllegalArgumentException) {
                    DEFAULT_REMOTE_NAVIGATION
                },
                useMinimalistRemote = preferences[useMinimalistRemoteKey] ?: DEFAULT_USE_MINIMALIST_REMOTE,
                useEnterForSelection = preferences[useEnterForSelectionKey] ?: DEFAULT_USE_ENTER_FOR_SELECTION,
                useMouseNavigationByDefault = preferences[useMouseNavigationByDefaultKey] ?: USE_MOUSE_NAVIGATION_BY_DEFAULT
            )
        }

    // ---- Mouse ----

    suspend fun saveMouseSpeed(mouseSpeed: Float) {
        context.dataStore.edit {
            it[mouseSpeedKey] = mouseSpeed
        }
    }

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        context.dataStore.edit {
            it[invertMouseScrollingDirectionKey] = invertScrollingDirection
        }
    }

    suspend fun saveUseGyroscope(useGyroscope: Boolean) {
        context.dataStore.edit {
            it[useGyroscopeKey] = useGyroscope
        }
    }

    // ---- Keyboard ----

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        context.dataStore.edit {
            it[keyboardLanguageKey] = language.name
        }
    }

    suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        context.dataStore.edit {
            it[mustClearInputFieldKey] = mustClearInputField
        }
    }

    suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) {
        context.dataStore.edit {
            it[useAdvancedKeyboardKey] = useAdvancedKeyboard
        }
    }

    suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean) {
        context.dataStore.edit {
            it[useAdvancedKeyboardIntegratedKey] = useAdvancedKeyboardIntegrated
        }
    }

    // ---- Remote ----

    suspend fun saveUseMinimalistRemote(useAdvancedKeyboard: Boolean) {
        context.dataStore.edit {
            it[useMinimalistRemoteKey] = useAdvancedKeyboard
        }
    }

    suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity) {
        context.dataStore.edit {
            it[remoteNavigationKey] = remoteNavigationEntity.name
        }
    }

    suspend fun saveUseEnterForSelection(useEnterForSelection: Boolean) {
        context.dataStore.edit {
            it[useEnterForSelectionKey] = useEnterForSelection
        }
    }

    suspend fun saveUseMouseNavigationByDefault(useMouseNavigationByDefault: Boolean) {
        context.dataStore.edit {
            it[useMouseNavigationByDefaultKey] = useMouseNavigationByDefault
        }
    }

    // ---- Favorite Devices ----

    val favoriteDevicesFlow: Flow<List<String>> = context.dataStore.data
        .catchException()
        .map {
            val jsonString: String? = it[favoriteDevicesKey]
            if(jsonString == null) emptyList() else Json.decodeFromString(jsonString)
        }

    suspend fun saveFavoriteDevices(macAddresses: List<String>) {
        val jsonString = Json.encodeToString(macAddresses)
        context.dataStore.edit {
            it[favoriteDevicesKey] = jsonString
        }
    }

    // ---- Auto Connect ----

    val autoConnectDeviceAddressFlow: Flow<String> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[autoConnectDeviceAddressKey] ?: ""
            }
    }

    suspend fun saveAutoConnectDeviceAddress(macAddress: String) {
        context.dataStore.edit {
            it[autoConnectDeviceAddressKey] = macAddress
        }
    }

    // ---- Advanced Options ----

    val hideBluetoothActivationButtonFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[hideBluetoothActivationButtonKey] == true
            }
    }

    suspend fun saveHideBluetoothActivationButton(hide: Boolean) {
        context.dataStore.edit {
            it[hideBluetoothActivationButtonKey] = hide
        }
    }
}