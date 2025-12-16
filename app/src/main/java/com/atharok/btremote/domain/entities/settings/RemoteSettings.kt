package com.atharok.btremote.domain.entities.settings

import com.atharok.btremote.common.utils.DEFAULT_KEYBOARD_LANGUAGE
import com.atharok.btremote.common.utils.DEFAULT_MOUSE_SPEED
import com.atharok.btremote.common.utils.DEFAULT_MUST_CLEAR_INPUT_FIELD
import com.atharok.btremote.common.utils.DEFAULT_REMOTE_NAVIGATION
import com.atharok.btremote.common.utils.DEFAULT_SCROLL_SPEED
import com.atharok.btremote.common.utils.DEFAULT_SHOULD_INVERT_MOUSE_SCROLLING_DIRECTION
import com.atharok.btremote.common.utils.DEFAULT_USE_ADVANCED_KEYBOARD
import com.atharok.btremote.common.utils.DEFAULT_USE_ADVANCED_KEYBOARD_INTEGRATED
import com.atharok.btremote.common.utils.DEFAULT_USE_ENTER_FOR_SELECTION
import com.atharok.btremote.common.utils.DEFAULT_USE_GYROSCOPE
import com.atharok.btremote.common.utils.DEFAULT_USE_MINIMALIST_REMOTE
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage

data class RemoteSettings(
    // ---- Mouse ----
    val mouseSpeed: Float = DEFAULT_MOUSE_SPEED,
    val scrollSpeed: Float = DEFAULT_SCROLL_SPEED,
    val shouldInvertMouseScrollingDirection: Boolean = DEFAULT_SHOULD_INVERT_MOUSE_SCROLLING_DIRECTION,
    val useGyroscope: Boolean = DEFAULT_USE_GYROSCOPE,

    // ---- Keyboard ----
    val keyboardLanguage: KeyboardLanguage = DEFAULT_KEYBOARD_LANGUAGE,
    val mustClearInputField: Boolean = DEFAULT_MUST_CLEAR_INPUT_FIELD,
    val useAdvancedKeyboard: Boolean = DEFAULT_USE_ADVANCED_KEYBOARD,
    val useAdvancedKeyboardIntegrated: Boolean = DEFAULT_USE_ADVANCED_KEYBOARD_INTEGRATED,

    // ---- Remote ----
    val remoteNavigationEntity: RemoteNavigationEntity = DEFAULT_REMOTE_NAVIGATION,
    val useMinimalistRemote: Boolean = DEFAULT_USE_MINIMALIST_REMOTE,
    val useEnterForSelection: Boolean = DEFAULT_USE_ENTER_FOR_SELECTION
)