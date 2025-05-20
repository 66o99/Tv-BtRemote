package com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard

import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

class PersianVirtualKeyboardLayout: VirtualKeyboardLayout() {

    protected override val keyboardInputs: Map<Char, ByteArray> by lazy {
        mapOf(
            ' ' to KEYBOARD_KEY_SPACE_BAR,

            '÷' to byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_00.byte),
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

            'ض' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_00.byte),
            'ص' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_01.byte),
            'ث' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_02.byte),
            'ق' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_03.byte),
            'ف' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_04.byte),
            'غ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_05.byte),
            'ع' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_06.byte),
            'ه' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_07.byte),
            'خ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_08.byte),
            'ح' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_09.byte),
            'ج' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_10.byte),
            'چ' to byteArrayOf(0x00, KeyboardKey.ROW_2_KEY_11.byte),

            'ش' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_00.byte),
            'س' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_01.byte),
            'ی' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_02.byte),
            'ب' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_03.byte),
            'ل' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_04.byte),
            'ا' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_05.byte),
            'ت' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_06.byte),
            'ن' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_07.byte),
            'م' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_08.byte),
            'ک' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_09.byte),
            'گ' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_10.byte),
            'پ' to byteArrayOf(0x00, KeyboardKey.ROW_3_KEY_11.byte),

            'پ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_00.byte),
            'ظ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_01.byte),
            'ط' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_02.byte),
            'ز' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_03.byte),
            'ر' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_04.byte),
            'ذ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_05.byte),
            'د' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_06.byte),
            'ئ' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_07.byte),
            'و' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_08.byte),
            '.' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_09.byte),
            '/' to byteArrayOf(0x00, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Shift ----

            '×' to byteArrayOf(0x02, KeyboardKey.ROW_1_KEY_00.byte),
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

            'ً' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_00.byte),
            'ٌ' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_01.byte),
            'ٍ' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_02.byte),
            '﷼' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_03.byte),
            '،' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_04.byte),
            '؛' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_05.byte),
            ',' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_06.byte),
            ']' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_07.byte),
            '[' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_08.byte),
            '\\' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_09.byte),
            '}' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_10.byte),
            '{' to byteArrayOf(0x02, KeyboardKey.ROW_2_KEY_11.byte),

            'َ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_00.byte),
            'ُ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_01.byte),
            'ِ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_02.byte),
            'ّ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_03.byte),
            'ۀ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_04.byte),
            'آ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_05.byte),
            'ـ' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_06.byte),
            '«' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_07.byte),
            '»' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_08.byte),
            ':' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_09.byte),
            '"' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_10.byte),
            '|' to byteArrayOf(0x02, KeyboardKey.ROW_3_KEY_11.byte),

            'ة' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_01.byte),
            'ي' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_02.byte),
            'ژ' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_03.byte),
            'ؤ' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_04.byte),
            'أ' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_05.byte),
            'إ' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_06.byte),
            'ء' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_07.byte),
            '<' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_08.byte),
            '>' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_09.byte),
            '؟' to byteArrayOf(0x02, KeyboardKey.ROW_4_KEY_10.byte),

            // ---- Alt Gr ----

            '۱' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_01.byte),
            '۲' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_02.byte),
            '۳' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_03.byte),
            '۴' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_04.byte),
            '۵' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_05.byte),
            '۶' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_06.byte),
            '۷' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_07.byte),
            '۸' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_08.byte),
            '۹' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_09.byte),
            '۰' to byteArrayOf(0x40, KeyboardKey.ROW_1_KEY_10.byte)
        )
    }

    override val extraInputs: Map<Char, ByteArray> = emptyMap()
}