package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import androidx.compose.ui.text.style.TextAlign
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class ANSIEnglishUSAdvancedKeyboardLayout(context: Context) : AdvancedKeyboardLayout(context) {

    override val line1: Array<AdvancedKeyboardKey> = arrayOf()
    override val line2: Array<AdvancedKeyboardKey> = arrayOf()
    override val line3: Array<AdvancedKeyboardKey> = arrayOf()
    override val line4: Array<AdvancedKeyboardKey> = arrayOf()

    override val layout by lazy {

        arrayOf<Array<AdvancedKeyboardKey>>(

            arrayOf(
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_ESCAPE.byte,
                    weight = 1.5f,
                    text = " ESC "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F1.byte,
                    weight = 1f,
                    text = " F1 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F2.byte,
                    weight = 1f,
                    text = " F2 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F3.byte,
                    weight = 1f,
                    text = " F3 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F4.byte,
                    weight = 1f,
                    text = " F4 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F5.byte,
                    weight = 1f,
                    text = " F5 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F6.byte,
                    weight = 1f,
                    text = " F6 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F7.byte,
                    weight = 1f,
                    text = " F7 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F8.byte,
                    weight = 1f,
                    text = " F8 "
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_F9.byte,
                    weight = 1f,
                    text = " F9 "
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
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_INSERT.byte,
                    fraction = 0.06f,
                    text = " INS "
                ),
            ),

            arrayOf(
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_00.byte,
                    weight = 1f,
                    text = "`",
                    textSecondary = "~"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_01.byte,
                    weight = 1f,
                    text = "1",
                    textSecondary = "!"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_02.byte,
                    weight = 1f,
                    text = "2",
                    textSecondary = "@"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_03.byte,
                    weight = 1f,
                    text = "3",
                    textSecondary = "#"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_04.byte,
                    weight = 1f,
                    text = "4",
                    textSecondary = "$"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_05.byte,
                    weight = 1f,
                    text = "5",
                    textSecondary = "%"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_06.byte,
                    weight = 1f,
                    text = "6",
                    textSecondary = "^"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_07.byte,
                    weight = 1f,
                    text = "7",
                    textSecondary = "&"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_08.byte,
                    weight = 1f,
                    text = "8",
                    textSecondary = "*"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_09.byte,
                    weight = 1f,
                    text = "9",
                    textSecondary = "("
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_10.byte,
                    weight = 1f,
                    text = "0",
                    textSecondary = ")"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_11.byte,
                    weight = 1f,
                    text = "-",
                    textSecondary = "_"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_1_KEY_12.byte,
                    weight = 1f,
                    text = "=",
                    textSecondary = "+"
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_BACKSPACE.byte,
                    weight = 1.75f,
                    icon = AppIcons.KeyboardBackspace
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_DELETE.byte,
                    fraction = 0.06f,
                    text = " DEL "
                )
            ),

            arrayOf(
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_TAB.byte,
                    weight = 1.5f,
                    icon = AppIcons.KeyboardTab
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_00.byte,
                    weight = 1f,
                    text = "Q"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_01.byte,
                    weight = 1f,
                    text = "W"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_02.byte,
                    weight = 1f,
                    text = "E"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_03.byte,
                    weight = 1f,
                    text = "R"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_04.byte,
                    weight = 1f,
                    text = "T"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_05.byte,
                    weight = 1f,
                    text = "Y"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_06.byte,
                    weight = 1f,
                    text = "U"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_07.byte,
                    weight = 1f,
                    text = "I"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_08.byte,
                    weight = 1f,
                    text = "O"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_09.byte,
                    weight = 1f,
                    text = "P"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_10.byte,
                    weight = 1f,
                    text = "[",
                    textSecondary = "{"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_2_KEY_11.byte,
                    weight = 1f,
                    text = "]",
                    textSecondary = "}"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_11.byte,
                    weight = 1.5f,
                    text = "\\",
                    textSecondary = "|"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_HOME.byte,
                    fraction = 0.06f,
                    text = "HOME"
                )
            ),

            arrayOf(
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_CAPSLOCK.byte,
                    weight = 1.75f,
                    icon = AppIcons.KeyboardCapslock
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_00.byte,
                    weight = 1f,
                    text = "A"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_01.byte,
                    weight = 1f,
                    text = "S"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_02.byte,
                    weight = 1f,
                    text = "D"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_03.byte,
                    weight = 1f,
                    text = "F"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_04.byte,
                    weight = 1f,
                    text = "G"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_05.byte,
                    weight = 1f,
                    text = "H"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_06.byte,
                    weight = 1f,
                    text = "J"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_07.byte,
                    weight = 1f,
                    text = "K"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_08.byte,
                    weight = 1f,
                    text = "L"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_09.byte,
                    weight = 1f,
                    text = ";",
                    textSecondary = ":"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_3_KEY_10.byte,
                    weight = 1f,
                    text = "\'",
                    textSecondary = "\""
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_ENTER.byte,
                    weight = 1.75f,
                    icon = AppIcons.KeyboardEnter
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_END.byte,
                    fraction = 0.06f,
                    text = " END "
                )
            ),

            arrayOf(
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_SHIFT_LEFT.byte,
                    weight = 2f,
                    text = "Shift",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_01.byte,
                    weight = 1f,
                    text = "Z"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_02.byte,
                    weight = 1f,
                    text = "X"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_03.byte,
                    weight = 1f,
                    text = "C"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_04.byte,
                    weight = 1f,
                    text = "V"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_05.byte,
                    weight = 1f,
                    text = "B"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_06.byte,
                    weight = 1f,
                    text = "N"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_07.byte,
                    weight = 1f,
                    text = "M"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_08.byte,
                    weight = 1f,
                    text = ",",
                    textSecondary = "<"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_09.byte,
                    weight = 1f,
                    text = ".",
                    textSecondary = ">"
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.ROW_4_KEY_10.byte,
                    weight = 1f,
                    text = "/",
                    textSecondary = "?"
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_UP_ARROW.byte,
                    fraction = 0.06f,
                    icon = AppIcons.KeyboardArrowUp
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_SHIFT_RIGHT.byte,
                    fraction = 0.06f,
                    text = "Shift",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_PAGE_UP.byte,
                    fraction = 0.06f,
                    text = "PGUP"
                )
            ),

            arrayOf(
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_CTRL_LEFT.byte,
                    weight = 1.5f,
                    text = " Ctrl ",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_META_LEFT.byte,
                    weight = 1.5f,
                    text = " Meta ",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_ALT.byte,
                    weight = 1.5f,
                    text = "  Alt  ",
                    textAlign = TextAlign.Center
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_SPACE_BAR.byte,
                    weight = 3f,
                    icon = AppIcons.SpaceBar,
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_ALT_GR.byte,
                    weight = 1.5f,
                    text = "AltGr",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_META_RIGHT.byte,
                    weight = 1.5f,
                    text = " Meta ",
                    textAlign = TextAlign.Center
                ),
                TextAdvancedKeyboardModifierKey(
                    byte = KeyboardKey.KEY_CTRL_RIGHT.byte,
                    weight = 1.5f,
                    text = " Ctrl ",
                    textAlign = TextAlign.Center
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_LEFT_ARROW.byte,
                    fraction = 0.06f,
                    icon = AppIcons.KeyboardArrowLeft
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_DOWN_ARROW.byte,
                    fraction = 0.06f,
                    icon = AppIcons.KeyboardArrowDown
                ),
                IconAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_RIGHT_ARROW.byte,
                    fraction = 0.06f,
                    icon = AppIcons.KeyboardArrowRight
                ),
                TextAdvancedKeyboardKey(
                    byte = KeyboardKey.KEY_PAGE_DOWN.byte,
                    fraction = 0.06f,
                    text = "PGDN"
                )
            )
        )
    }
}