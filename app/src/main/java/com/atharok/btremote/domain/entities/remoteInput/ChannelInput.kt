package com.atharok.btremote.domain.entities.remoteInput

import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey

object ChannelInput {
    val CHANNEL_INPUT_1 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_01.byte) }
    val CHANNEL_INPUT_2 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_02.byte) }
    val CHANNEL_INPUT_3 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_03.byte) }
    val CHANNEL_INPUT_4 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_04.byte) }
    val CHANNEL_INPUT_5 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_05.byte) }
    val CHANNEL_INPUT_6 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_06.byte) }
    val CHANNEL_INPUT_7 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_07.byte) }
    val CHANNEL_INPUT_8 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_08.byte) }
    val CHANNEL_INPUT_9 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_09.byte) }
    val CHANNEL_INPUT_0 by lazy { byteArrayOf(0x00, KeyboardKey.ROW_1_KEY_10.byte) }
}