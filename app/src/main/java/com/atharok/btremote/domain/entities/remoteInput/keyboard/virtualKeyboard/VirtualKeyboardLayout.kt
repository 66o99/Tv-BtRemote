package com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard

import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

// https://source.android.com/docs/core/interaction/input/keyboard-devices#hid-keyboard-and-keypad-page-0x07
abstract class VirtualKeyboardLayout {
    companion object {
        val KEYBOARD_KEY_ENTER by lazy { byteArrayOf(0x00, KeyboardKey.KEY_ENTER.byte) }
        val KEYBOARD_KEY_BACKSPACE by lazy { byteArrayOf(0x00, KeyboardKey.KEY_BACKSPACE.byte) }
        val KEYBOARD_KEY_SPACE_BAR by lazy { byteArrayOf(0x00, KeyboardKey.KEY_SPACE_BAR.byte) }
        val KEYBOARD_KEY_UP by lazy { byteArrayOf(0x00, KeyboardKey.KEY_UP_ARROW.byte) }
        val KEYBOARD_KEY_DOWN by lazy { byteArrayOf(0x00, KeyboardKey.KEY_DOWN_ARROW.byte) }
        val KEYBOARD_KEY_LEFT by lazy { byteArrayOf(0x00, KeyboardKey.KEY_LEFT_ARROW.byte) }
        val KEYBOARD_KEY_RIGHT by lazy { byteArrayOf(0x00, KeyboardKey.KEY_RIGHT_ARROW.byte) }
        val KEYBOARD_KEY_PRINT_SCREEN by lazy { byteArrayOf(0x00, KeyboardKey.KEY_PRINT_SCREEN.byte) }
    }

    private val inputs: Map<Char, ByteArray> by lazy { keyboardInputs + extraInputs }

    protected abstract val keyboardInputs: Map<Char, ByteArray>
    protected abstract val extraInputs: Map<Char, ByteArray>

    fun getKeyboardKey(key: Char): ByteArray = inputs[key] ?: REMOTE_INPUT_NONE
}