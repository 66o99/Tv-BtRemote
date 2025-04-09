package com.atharok.btremote.domain.entities.remoteInput.keyboard

enum class KeyboardKey(val byte: Byte) {
    ROW_1_KEY_00(0x35), // `
    ROW_1_KEY_01(0x1E), // 1
    ROW_1_KEY_02(0x1F), // 2
    ROW_1_KEY_03(0x20), // 3
    ROW_1_KEY_04(0x21), // 4
    ROW_1_KEY_05(0x22), // 5
    ROW_1_KEY_06(0x23), // 6
    ROW_1_KEY_07(0x24), // 7
    ROW_1_KEY_08(0x25), // 8
    ROW_1_KEY_09(0x26), // 9
    ROW_1_KEY_10(0x27), // 0
    ROW_1_KEY_11(0x2D), // -
    ROW_1_KEY_12(0x2E), // =

    ROW_2_KEY_00(0x14), // q
    ROW_2_KEY_01(0x1A), // w
    ROW_2_KEY_02(0x08), // e
    ROW_2_KEY_03(0x15), // r
    ROW_2_KEY_04(0x17), // t
    ROW_2_KEY_05(0x1C), // y
    ROW_2_KEY_06(0x18), // u
    ROW_2_KEY_07(0x0C), // i
    ROW_2_KEY_08(0x12), // o
    ROW_2_KEY_09(0x13), // p
    ROW_2_KEY_10(0x2F), // [
    ROW_2_KEY_11(0x30), // ]

    ROW_3_KEY_00(0x04), // a
    ROW_3_KEY_01(0x16), // s
    ROW_3_KEY_02(0x07), // d
    ROW_3_KEY_03(0x09), // f
    ROW_3_KEY_04(0x0A), // g
    ROW_3_KEY_05(0x0B), // h
    ROW_3_KEY_06(0x0D), // j
    ROW_3_KEY_07(0x0E), // k
    ROW_3_KEY_08(0x0F), // l
    ROW_3_KEY_09(0x33), // ;
    ROW_3_KEY_10(0x34), // '
    ROW_3_KEY_11(0x31), // \

    ROW_4_KEY_00(0x64), // <
    ROW_4_KEY_01(0x1D), // z
    ROW_4_KEY_02(0x1B), // x
    ROW_4_KEY_03(0x06), // c
    ROW_4_KEY_04(0x19), // v
    ROW_4_KEY_05(0x05), // b
    ROW_4_KEY_06(0x11), // n
    ROW_4_KEY_07(0x10), // m
    ROW_4_KEY_08(0x36), // ,
    ROW_4_KEY_09(0x37), // .
    ROW_4_KEY_10(0x38), // /

    KEY_F1(0x3A),
    KEY_F2(0x3B),
    KEY_F3(0x3C),
    KEY_F4(0x3D),
    KEY_F5(0x3E),
    KEY_F6(0x3F),
    KEY_F7(0x40),
    KEY_F8(0x41),
    KEY_F9(0x42),
    KEY_F10(0x43),
    KEY_F11(0x44),
    KEY_F12(0x45),
    KEY_PRINT_SCREEN(0x46),
    KEY_ENTER(0x28),
    KEY_ESCAPE(0x29),
    KEY_DELETE(0x2A),
    KEY_TAB(0x2B),
    KEY_SPACE_BAR(0x2C),
    KEY_RIGHT_ARROW(0x4F),
    KEY_LEFT_ARROW(0x50),
    KEY_DOWN_ARROW(0x51),
    KEY_UP_ARROW(0x52),

    // Modifier keys
    KEY_SHIFT_LEFT(0x02),
    KEY_SHIFT_RIGHT(0x20),
    KEY_META_LEFT(0x08),
    KEY_META_RIGHT(0x80.toByte()),
    KEY_CTRL_LEFT(0x01),
    KEY_CTRL_RIGHT(0x10),
    KEY_ALT(0x04),
    KEY_ALT_GR(0x40)
}