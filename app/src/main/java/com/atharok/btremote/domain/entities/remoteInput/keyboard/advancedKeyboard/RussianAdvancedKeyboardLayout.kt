package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class RussianAdvancedKeyboardLayout(context: Context): AdvancedKeyboardLayout(context) {

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
            textSecondary = "\"",
            textTertiary = "@"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_03.byte,
            weight = 1f,
            text = "3",
            textSecondary = "№",
            textTertiary = "#"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_04.byte,
            weight = 1f,
            text = "4",
            textSecondary = ";",
            textTertiary = "$"
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
            textSecondary = ":",
            textTertiary = "^"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_07.byte,
            weight = 1f,
            text = "7",
            textSecondary = "?",
            textTertiary = "&"
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
            text = "Й",
            textTertiary = "Q"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_01.byte,
            weight = 1f,
            text = "Ц",
            textTertiary = "W"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_02.byte,
            weight = 1f,
            text = "У",
            textTertiary = "E"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_03.byte,
            weight = 1f,
            text = "К",
            textTertiary = "R"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_04.byte,
            weight = 1f,
            text = "Е",
            textTertiary = "T"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_05.byte,
            weight = 1f,
            text = "Н",
            textTertiary = "Y"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_06.byte,
            weight = 1f,
            text = "Г",
            textTertiary = "U"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_07.byte,
            weight = 1f,
            text = "Ш",
            textTertiary = "I"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_08.byte,
            weight = 1f,
            text = "Щ",
            textTertiary = "O"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_09.byte,
            weight = 1f,
            text = "З",
            textTertiary = "P"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_10.byte,
            weight = 1f,
            text = "Х",
            textSecondary = "{",
            textTertiary = "["
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_11.byte,
            weight = 1f,
            text = "Ъ",
            textSecondary = "}",
            textTertiary = "]"
        )
    )

    override val line3: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_00.byte,
            weight = 1f,
            text = "Ф",
            textTertiary = "A"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_01.byte,
            weight = 1f,
            text = "Ы",
            textTertiary = "S"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_02.byte,
            weight = 1f,
            text = "В",
            textTertiary = "D"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_03.byte,
            weight = 1f,
            text = "А",
            textTertiary = "F"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_04.byte,
            weight = 1f,
            text = "П",
            textTertiary = "G"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_05.byte,
            weight = 1f,
            text = "Р",
            textTertiary = "H"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_06.byte,
            weight = 1f,
            text = "О",
            textTertiary = "J"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_07.byte,
            weight = 1f,
            text = "Л",
            textTertiary = "K"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_08.byte,
            weight = 1f,
            text = "Д",
            textTertiary = "L"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_09.byte,
            weight = 1f,
            text = "Ж",
            textSecondary = ":",
            textTertiary = ";"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_10.byte,
            weight = 1f,
            text = "Э",
            textSecondary = "\"",
            textTertiary = "'"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_11.byte,
            weight = 1f,
            text = "\\",
            textSecondary = "/",
            textTertiary = "|"
        )
    )

    override val line4: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_00.byte,
            weight = 1f,
            text = "Ё",
            textSecondary = "~",
            textTertiary = "`"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_00.byte,
            weight = 1f,
            text = "\\",
            textSecondary = "/",
            textTertiary = "|"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_01.byte,
            weight = 1f,
            text = "Я",
            textTertiary = "Z"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_02.byte,
            weight = 1f,
            text = "Ч",
            textTertiary = "X"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_03.byte,
            weight = 1f,
            text = "С",
            textTertiary = "C"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_04.byte,
            weight = 1f,
            text = "М",
            textTertiary = "V"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_05.byte,
            weight = 1f,
            text = "И",
            textTertiary = "B"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_06.byte,
            weight = 1f,
            text = "Т",
            textTertiary = "N"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_07.byte,
            weight = 1f,
            text = "Ь",
            textTertiary = "M"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_08.byte,
            weight = 1f,
            text = "Б",
            textSecondary = "<",
            textTertiary = ","
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_09.byte,
            weight = 1f,
            text = "Ю",
            textSecondary = ">",
            textTertiary = "."
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_10.byte,
            weight = 1f,
            text = ".",
            textSecondary = ",    ?",
            textTertiary = "/"
        )
    )
}