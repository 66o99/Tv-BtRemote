package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class EnglishUKAdvancedKeyboardLayout(context: Context): AdvancedKeyboardLayout(context) {

    override val line1: Array<AdvancedKeyboardKey> = arrayOf(
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
            textSecondary = "\""
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_03.byte,
            weight = 1f,
            text = "3",
            textSecondary = "£"
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
        )
    )

    override val line2: Array<AdvancedKeyboardKey> = arrayOf(
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
        )
    )

    override val line3: Array<AdvancedKeyboardKey> = arrayOf(
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
            textSecondary = "@"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_11.byte,
            weight = 1f,
            text = "#",
            textSecondary = "~"
        )
    )

    override val line4: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_00.byte,
            weight = 1f,
            text = "`",
            textSecondary = "¬"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_00.byte,
            weight = 1f,
            text = "\\",
            textSecondary = "|"
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
        )
    )
}