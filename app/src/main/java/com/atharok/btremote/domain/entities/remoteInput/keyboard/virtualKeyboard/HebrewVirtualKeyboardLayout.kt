package com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard

import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class HebrewVirtualKeyboardLayout: VirtualKeyboardLayout() {

    protected override val keyboardInputs: Map<Char, ByteArray> by lazy {
        mapOf(
            ' ' to KEYBOARD_KEY_SPACE_BAR,

            ';' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_00.byte),
            '1' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_01.byte),
            '2' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_02.byte),
            '3' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_03.byte),
            '4' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_04.byte),
            '5' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_05.byte),
            '6' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_06.byte),
            '7' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_07.byte),
            '8' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_08.byte),
            '9' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_09.byte),
            '0' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_10.byte),
            '-' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_11.byte),
            '=' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_12.byte),

            '/' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_00.byte),
            '\'' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_01.byte),
            'ק' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_02.byte),
            'ר' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_03.byte),
            'א' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_04.byte),
            'ט' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_05.byte),
            'ו' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_06.byte),
            'ן' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_07.byte),
            'ם' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_08.byte),
            'פ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_09.byte),
            ']' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_10.byte),
            '[' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_11.byte),

            'ש' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_00.byte),
            'ד' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_01.byte),
            'ג' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_02.byte),
            'כ' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_03.byte),
            'ע' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_04.byte),
            'י' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_05.byte),
            'ח' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_06.byte),
            'ל' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_07.byte),
            'ך' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_08.byte),
            'ף' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_09.byte),
            ',' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_10.byte),
            '\\' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_11.byte),

            //'\\' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_00.byte),
            'ז' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_01.byte),
            'ס' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_02.byte),
            'ב' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_03.byte),
            'ה' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_04.byte),
            'נ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_05.byte),
            'מ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_06.byte),
            'צ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_07.byte),
            'ת' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_08.byte),
            'ץ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_09.byte),
            '.' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Shift ----

            '~' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_00.byte),
            '!' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_01.byte),
            '@' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_02.byte),
            '#' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_03.byte),
            '$' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_04.byte),
            '%' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_05.byte),
            '^' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_06.byte),
            '&' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_07.byte),
            '*' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_08.byte),
            ')' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_09.byte),
            '(' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_10.byte),
            '_' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_11.byte),
            '+' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_12.byte),

            'Q' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_00.byte),
            'W' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_01.byte),
            'E' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_02.byte),
            'R' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_03.byte),
            'T' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_04.byte),
            'Y' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_05.byte),
            'U' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_06.byte),
            'I' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_07.byte),
            'O' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_08.byte),
            'P' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_09.byte),
            '}' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_10.byte),
            '{' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_11.byte),

            'A' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_00.byte),
            'S' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_01.byte),
            'D' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_02.byte),
            'F' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_03.byte),
            'G' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_04.byte),
            'H' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_05.byte),
            'J' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_06.byte),
            'K' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_07.byte),
            'L' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_08.byte),
            ':' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_09.byte),
            '"' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_10.byte),
            '|' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_11.byte),

            'Z' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_01.byte),
            'X' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_02.byte),
            'C' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_03.byte),
            'V' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_04.byte),
            'B' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_05.byte),
            'N' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_06.byte),
            'M' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_07.byte),
            '>' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_08.byte),
            '<' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_09.byte),
            '?' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_10.byte)
        )
    }

    protected override val extraInputs: Map<Char, ByteArray> by lazy {
        mapOf(
            '¹' to (keyboardInputs['1'] ?: REMOTE_INPUT_NONE),
            '²' to (keyboardInputs['2'] ?: REMOTE_INPUT_NONE),
            '³' to (keyboardInputs['3'] ?: REMOTE_INPUT_NONE),
            '₪' to (keyboardInputs['4'] ?: REMOTE_INPUT_NONE)
        )
    }
}