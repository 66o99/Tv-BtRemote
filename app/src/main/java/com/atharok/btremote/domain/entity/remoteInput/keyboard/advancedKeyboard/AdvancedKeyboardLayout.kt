package com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import androidx.compose.ui.text.style.TextAlign
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardKey

abstract class AdvancedKeyboardLayout(protected val context: Context) {

    abstract val line1: Array<AdvancedKeyboardKey>
    abstract val line2: Array<AdvancedKeyboardKey>
    abstract val line3: Array<AdvancedKeyboardKey>
    abstract val line4: Array<AdvancedKeyboardKey>

    val layout by lazy {

        arrayOf<Array<AdvancedKeyboardKey>>(

            // F1 to F12
            arrayOf(
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F1.byte,
                    weight = 1f,
                    text = "F1"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F2.byte,
                    weight = 1f,
                    text = "F2"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F3.byte,
                    weight = 1f,
                    text = "F3"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F4.byte,
                    weight = 1f,
                    text = "F4"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F5.byte,
                    weight = 1f,
                    text = "F5"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F6.byte,
                    weight = 1f,
                    text = "F6"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F7.byte,
                    weight = 1f,
                    text = "F7"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F8.byte,
                    weight = 1f,
                    text = "F8"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F9.byte,
                    weight = 1f,
                    text = "F9"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F10.byte,
                    weight = 1f,
                    text = "F10"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F11.byte,
                    weight = 1f,
                    text = "F11"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F12.byte,
                    weight = 1f,
                    text = "F12"
                )
            ),

            line1, line2, line3, line4,

            // Esc, Tab, Print Screen, Arrow Up, Backspace, Enter
            arrayOf(
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_ESCAPE.byte,
                    weight = 1f,
                    text = context.getString(R.string.escape)
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_TAB.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardTab
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_PRINT_SCREEN.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardScreenshot
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_UP_ARROW.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardArrowUp
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_DELETE.byte,
                    weight = 1.5f,
                    icon = AppIcons.KeyboardBackspace
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_ENTER.byte,
                    weight = 1.5f,
                    icon = AppIcons.KeyboardEnter
                )
            ),

            // LShift, LMeta, Arrow Left, Arrow Down, Arrow Right, RMeta, RShift
            arrayOf(
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_SHIFT_LEFT.byte,
                    weight = 1f,
                    text = context.getString(R.string.shift),
                    textAlign = TextAlign.Start
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_META_LEFT.byte,
                    weight = 1f,
                    text = context.getString(R.string.meta),
                    textAlign = TextAlign.Start
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_LEFT_ARROW.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardArrowLeft
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_DOWN_ARROW.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardArrowDown
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_RIGHT_ARROW.byte,
                    weight = 1f,
                    icon = AppIcons.KeyboardArrowRight
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_META_RIGHT.byte,
                    weight = 1f,
                    text = context.getString(R.string.meta),
                    textAlign = TextAlign.End
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_SHIFT_RIGHT.byte,
                    weight = 1f,
                    text = context.getString(R.string.shift),
                    textAlign = TextAlign.End
                )
            ),

            // LCtrl, Alt, Space bar, Alt Gr, RCtrl
            arrayOf(
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_CTRL_LEFT.byte,
                    weight = 1f,
                    text = context.getString(R.string.ctrl),
                    textAlign = TextAlign.Start
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_ALT.byte,
                    weight = 1f,
                    text = context.getString(R.string.alt),
                    textAlign = TextAlign.Center
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_SPACE_BAR.byte,
                    weight = 3f,
                    icon = AppIcons.SpaceBar
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_ALT_GR.byte,
                    weight = 1f,
                    text = context.getString(R.string.alt_gr),
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_CTRL_RIGHT.byte,
                    weight = 1f,
                    text = context.getString(R.string.ctrl),
                    textAlign = TextAlign.End
                )
            )
        )
    }
}