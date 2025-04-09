package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import android.content.Context
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class UkrainianAdvancedKeyboardLayout(context: Context): AdvancedKeyboardLayout(context) {

    override val line1: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_01.byte,
            weight = 1f,
            text = "1",
            textSecondary = "!",
            textTertiary = "¹"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_02.byte,
            weight = 1f,
            text = "2",
            textSecondary = "\"",
            textTertiary = "²"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_03.byte,
            weight = 1f,
            text = "3",
            textSecondary = "№",
            textTertiary = "§"
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
            textSecondary = "%",
            textTertiary = "°"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_06.byte,
            weight = 1f,
            text = "6",
            textSecondary = ":",
            textTertiary = "<"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_07.byte,
            weight = 1f,
            text = "7",
            textSecondary = "?",
            textTertiary = ">"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_08.byte,
            weight = 1f,
            text = "8",
            textSecondary = "*",
            textTertiary = "•"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_09.byte,
            weight = 1f,
            text = "9",
            textSecondary = "(",
            textTertiary = "["
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_10.byte,
            weight = 1f,
            text = "0",
            textSecondary = ")",
            textTertiary = "]"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_11.byte,
            weight = 1f,
            text = "-",
            textSecondary = "_",
            textTertiary = "—"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_12.byte,
            weight = 1f,
            text = "=",
            textSecondary = "+",
            textTertiary = "≠"
        )
    )

    override val line2: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_00.byte,
            weight = 1f,
            text = "й",
            textTertiary = "ј"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_01.byte,
            weight = 1f,
            text = "ц",
            textTertiary = "џ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_02.byte,
            weight = 1f,
            text = "у",
            textTertiary = "ў"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_03.byte,
            weight = 1f,
            text = "к",
            textTertiary = "®"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_04.byte,
            weight = 1f,
            text = "е",
            textTertiary = "ё"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_05.byte,
            weight = 1f,
            text = "н",
            textTertiary = "њ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_06.byte,
            weight = 1f,
            text = "г",
            textTertiary = "ґ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_07.byte,
            weight = 1f,
            text = "ш"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_08.byte,
            weight = 1f,
            text = "щ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_09.byte,
            weight = 1f,
            text = "з"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_10.byte,
            weight = 1f,
            text = "х"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_2_KEY_11.byte,
            weight = 1f,
            text = "ї",
            textTertiary = "ъ"
        )
    )

    override val line3: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_00.byte,
            weight = 1f,
            text = "ф"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_01.byte,
            weight = 1f,
            text = "і",
            textTertiary = "ы"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_02.byte,
            weight = 1f,
            text = "в"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_03.byte,
            weight = 1f,
            text = "а"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_04.byte,
            weight = 1f,
            text = "п"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_05.byte,
            weight = 1f,
            text = "р"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_06.byte,
            weight = 1f,
            text = "о"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_07.byte,
            weight = 1f,
            text = "л",
            textTertiary = "љ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_08.byte,
            weight = 1f,
            text = "д",
            textTertiary = "ђ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_09.byte,
            weight = 1f,
            text = "ж"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_10.byte,
            weight = 1f,
            text = "є",
            textTertiary = "э"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_3_KEY_11.byte,
            weight = 1f,
            text = "\\"
        )
    )

    override val line4: Array<AdvancedKeyboardKey> = arrayOf(
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_1_KEY_00.byte,
            weight = 1f,
            text = "'",
            textSecondary = "ʼ",
            textTertiary = "´"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_00.byte,
            weight = 1f,
            text = "ґ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_01.byte,
            weight = 1f,
            text = "я"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_02.byte,
            weight = 1f,
            text = "ч",
            textTertiary = "ћ"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_03.byte,
            weight = 1f,
            text = "с",
            textTertiary = "©"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_04.byte,
            weight = 1f,
            text = "м"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_05.byte,
            weight = 1f,
            text = "и"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_06.byte,
            weight = 1f,
            text = "т",
            textTertiary = "™"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_07.byte,
            weight = 1f,
            text = "ь"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_08.byte,
            weight = 1f,
            text = "б",
            textTertiary = "«"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_09.byte,
            weight = 1f,
            text = "ю",
            textTertiary = "»"
        ),
        TextAdvancedKeyboardKey(
            byte = KeyboardKey.ROW_4_KEY_10.byte,
            weight = 1f,
            text = ".",
            textSecondary = ","
        )
    )
}