package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class HebrewAdvancedKeyboardLayout(context: Context): AdvancedKeyboardLayout(context) {

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
            textSecondary = ")"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_10.byte,
            weight = 1f,
            text = "0",
            textSecondary = "("
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
            text = "/"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_01.byte,
            weight = 1f,
            text = "'"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_02.byte,
            weight = 1f,
            text = "ק"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_03.byte,
            weight = 1f,
            text = "ר"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_04.byte,
            weight = 1f,
            text = "א"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_05.byte,
            weight = 1f,
            text = "ט"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_06.byte,
            weight = 1f,
            text = "ו"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_07.byte,
            weight = 1f,
            text = "ן"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_08.byte,
            weight = 1f,
            text = "ם"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_09.byte,
            weight = 1f,
            text = "פ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_10.byte,
            weight = 1f,
            text = "]",
            textSecondary = "}"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_11.byte,
            weight = 1f,
            text = "[",
            textSecondary = "{"
        )
    )

    override val line3: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_00.byte,
            weight = 1f,
            text = "ש"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_01.byte,
            weight = 1f,
            text = "ד"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_02.byte,
            weight = 1f,
            text = "ג"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_03.byte,
            weight = 1f,
            text = "כ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_04.byte,
            weight = 1f,
            text = "ע"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_05.byte,
            weight = 1f,
            text = "י"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_06.byte,
            weight = 1f,
            text = "ח"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_07.byte,
            weight = 1f,
            text = "ל"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_08.byte,
            weight = 1f,
            text = "ך"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_09.byte,
            weight = 1f,
            text = "ף",
            textSecondary = ":"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_10.byte,
            weight = 1f,
            text = ",",
            textSecondary = "\""
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_11.byte,
            weight = 1f,
            text = "\\",
            textSecondary = "|"
        )
    )

    override val line4: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_00.byte,
            weight = 1f,
            text = ";",
            textSecondary = "~"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_00.byte,
            weight = 1f,
            text = "<",
            textSecondary = ">"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_01.byte,
            weight = 1f,
            text = "ז"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_02.byte,
            weight = 1f,
            text = "ס"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_03.byte,
            weight = 1f,
            text = "ב"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_04.byte,
            weight = 1f,
            text = "ה"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_05.byte,
            weight = 1f,
            text = "נ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_06.byte,
            weight = 1f,
            text = "מ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_07.byte,
            weight = 1f,
            text = "צ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_08.byte,
            weight = 1f,
            text = "ת",
            textSecondary = ">"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_09.byte,
            weight = 1f,
            text = "ץ",
            textSecondary = "<"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_10.byte,
            weight = 1f,
            text = ".",
            textSecondary = "?"
        )
    )
}