package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class GreekAdvancedKeyboardLayout(context: Context): AdvancedKeyboardLayout(context) {

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
            textSecondary = "@",
            textTertiary = "²"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_03.byte,
            weight = 1f,
            text = "3",
            textSecondary = "#",
            textTertiary = "³"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_04.byte,
            weight = 1f,
            text = "4",
            textSecondary = "$",
            textTertiary = "£"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_05.byte,
            weight = 1f,
            text = "5",
            textSecondary = "%",
            textTertiary = "§"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_06.byte,
            weight = 1f,
            text = "6",
            textSecondary = "^",
            textTertiary = "¶"
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
            textSecondary = "*",
            textTertiary = "¤"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_09.byte,
            weight = 1f,
            text = "9",
            textSecondary = "(",
            textTertiary = "¦"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_10.byte,
            weight = 1f,
            text = "0",
            textSecondary = ")",
            textTertiary = "°"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_11.byte,
            weight = 1f,
            text = "-",
            textSecondary = "_",
            textTertiary = "±"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_12.byte,
            weight = 1f,
            text = "=",
            textSecondary = "+",
            textTertiary = "½"
        )
    )

    override val line2: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_00.byte,
            weight = 1f,
            text = ";",
            textSecondary = ":"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_01.byte,
            weight = 1f,
            text = "ς",
            textSecondary = "΅"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_02.byte,
            weight = 1f,
            text = "ε",
            textTertiary = "€"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_03.byte,
            weight = 1f,
            text = "ρ",
            textTertiary = "®"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_04.byte,
            weight = 1f,
            text = "τ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_05.byte,
            weight = 1f,
            text = "υ",
            textTertiary = "¥"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_06.byte,
            weight = 1f,
            text = "θ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_07.byte,
            weight = 1f,
            text = "ι"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_08.byte,
            weight = 1f,
            text = "ο"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_09.byte,
            weight = 1f,
            text = "π"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_10.byte,
            weight = 1f,
            text = "[",
            textSecondary = "{",
            textTertiary = "«"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_11.byte,
            weight = 1f,
            text = "]",
            textSecondary = "}",
            textTertiary = "»"
        )
    )

    override val line3: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_00.byte,
            weight = 1f,
            text = "α"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_01.byte,
            weight = 1f,
            text = "σ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_02.byte,
            weight = 1f,
            text = "δ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_03.byte,
            weight = 1f,
            text = "φ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_04.byte,
            weight = 1f,
            text = "γ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_05.byte,
            weight = 1f,
            text = "η"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_06.byte,
            weight = 1f,
            text = "ξ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_07.byte,
            weight = 1f,
            text = "κ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_08.byte,
            weight = 1f,
            text = "λ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_09.byte,
            weight = 1f,
            text = "´",
            textSecondary = "¨",
            textTertiary = "΅"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_10.byte,
            weight = 1f,
            text = "\'",
            textSecondary = "\""
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_11.byte,
            weight = 1f,
            text = "\\",
            textSecondary = "|",
            textTertiary = "¬"
        )
    )

    override val line4: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_00.byte,
            weight = 1f,
            text = "`",
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
            text = "ζ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_02.byte,
            weight = 1f,
            text = "χ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_03.byte,
            weight = 1f,
            text = "ψ",
            textTertiary = "©"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_04.byte,
            weight = 1f,
            text = "ω"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_05.byte,
            weight = 1f,
            text = "β"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_06.byte,
            weight = 1f,
            text = "ν"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_07.byte,
            weight = 1f,
            text = "μ"
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