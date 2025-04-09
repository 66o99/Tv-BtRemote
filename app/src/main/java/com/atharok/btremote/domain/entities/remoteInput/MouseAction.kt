package com.atharok.btremote.domain.entities.remoteInput

enum class MouseAction(val byte: Byte) {
    NONE(0x00),
    MOUSE_CLICK_LEFT(0x01),
    MOUSE_CLICK_RIGHT(0x02),
    MOUSE_CLICK_MIDDLE(0x04),
    PAD_TAP(0x01)
}