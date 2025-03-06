package com.atharok.btremote.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.atharok.btremote.common.extensions.dataStore
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entity.RemoteNavigationEntity
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
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

    private fun Flow<Preferences>.catchException(): Flow<Preferences> = this.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }

    // ---- Appearance ----

    val themeFlow: Flow<ThemeEntity> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[themeKey] ?: ThemeEntity.SYSTEM.name
            }.map {
                try {
                    ThemeEntity.valueOf(it)
                } catch (e: IllegalArgumentException) {
                    ThemeEntity.SYSTEM
                }
            }
    }

    suspend fun saveTheme(themeEntity: ThemeEntity) {
        context.dataStore.edit {
            it[themeKey] = themeEntity.name
        }
    }

    val useDynamicColorsFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useDynamicColorsKey] ?: isDynamicColorsAvailable()
            }
    }

    suspend fun saveUseDynamicColors(useDynamicColors: Boolean) {
        context.dataStore.edit {
            it[useDynamicColorsKey] = if(isDynamicColorsAvailable()) useDynamicColors else false
        }
    }

    val useBlackColorForDarkThemeFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useBlackColorForDarkThemeKey] ?: false
            }
    }

    suspend fun saveUseBlackColorForDarkTheme(useBlackColorForDarkTheme: Boolean) {
        context.dataStore.edit {
            it[useBlackColorForDarkThemeKey] = useBlackColorForDarkTheme
        }
    }

    val useFullScreenFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useFullScreenKey] ?: false
            }
    }

    suspend fun saveUseFullScreen(useFullScreen: Boolean) {
        context.dataStore.edit {
            it[useFullScreenKey] = useFullScreen
        }
    }

    // ---- Mouse ----

    val mouseSpeed: Flow<Float> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[mouseSpeedKey] ?: MOUSE_SPEED_DEFAULT_VALUE
            }
    }

    suspend fun saveMouseSpeed(mouseSpeed: Float) {
        context.dataStore.edit {
            it[mouseSpeedKey] = mouseSpeed
        }
    }

    val shouldInvertMouseScrollingDirection: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[invertMouseScrollingDirectionKey] ?: false
            }
    }

    suspend fun saveInvertMouseScrollingDirection(invertScrollingDirection: Boolean) {
        context.dataStore.edit {
            it[invertMouseScrollingDirectionKey] = invertScrollingDirection
        }
    }

    val useGyroscope: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useGyroscopeKey] ?: false
            }
    }

    suspend fun saveUseGyroscope(useGyroscope: Boolean) {
        context.dataStore.edit {
            it[useGyroscopeKey] = useGyroscope
        }
    }

    // ---- Keyboard ----

    val keyboardLanguageFlow: Flow<KeyboardLanguage> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[keyboardLanguageKey] ?: KeyboardLanguage.ENGLISH_US.name
            }.map {
                try {
                    KeyboardLanguage.valueOf(it)
                } catch (e: IllegalArgumentException) {
                    KeyboardLanguage.ENGLISH_US
                }
            }
    }

    suspend fun saveKeyboardLanguage(language: KeyboardLanguage) {
        context.dataStore.edit {
            it[keyboardLanguageKey] = language.name
        }
    }

    val mustClearInputFieldFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[mustClearInputFieldKey] ?: true
            }
    }

    suspend fun saveMustClearInputField(mustClearInputField: Boolean) {
        context.dataStore.edit {
            it[mustClearInputFieldKey] = mustClearInputField
        }
    }

    val useAdvancedKeyboardFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useAdvancedKeyboardKey] ?: false
            }
    }

    suspend fun saveUseAdvancedKeyboard(useAdvancedKeyboard: Boolean) {
        context.dataStore.edit {
            it[useAdvancedKeyboardKey] = useAdvancedKeyboard
        }
    }

    val useAdvancedKeyboardIntegratedFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useAdvancedKeyboardIntegratedKey] ?: false
            }
    }

    suspend fun saveUseAdvancedKeyboardIntegrated(useAdvancedKeyboardIntegrated: Boolean) {
        context.dataStore.edit {
            it[useAdvancedKeyboardIntegratedKey] = useAdvancedKeyboardIntegrated
        }
    }

    // ---- Interface ----

    val useMinimalistRemoteFlow: Flow<Boolean> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[useMinimalistRemoteKey] ?: false
            }
    }

    suspend fun saveUseMinimalistRemote(useAdvancedKeyboard: Boolean) {
        context.dataStore.edit {
            it[useMinimalistRemoteKey] = useAdvancedKeyboard
        }
    }

    val remoteNavigationFlow: Flow<RemoteNavigationEntity> by lazy {
        context.dataStore.data
            .catchException()
            .map { preferences ->
                preferences[remoteNavigationKey] ?: RemoteNavigationEntity.D_PAD.name
            }.map {
                try {
                    RemoteNavigationEntity.valueOf(it)
                } catch (e: IllegalArgumentException) {
                    RemoteNavigationEntity.D_PAD
                }
            }
    }

    suspend fun saveRemoteNavigation(remoteNavigationEntity: RemoteNavigationEntity) {
        context.dataStore.edit {
            it[remoteNavigationKey] = remoteNavigationEntity.name
        }
    }
}