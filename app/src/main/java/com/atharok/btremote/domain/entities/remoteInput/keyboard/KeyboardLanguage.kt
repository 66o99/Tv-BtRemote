package com.atharok.btremote.domain.entities.remoteInput.keyboard

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class KeyboardLanguage(@param:StringRes val language: Int) {
    ENGLISH_US(R.string.keyboard_english_us),
    ENGLISH_UK(R.string.keyboard_english_uk),
    SPANISH(R.string.keyboard_spanish),
    FRENCH(R.string.keyboard_french),
    GERMAN(R.string.keyboard_german),
    RUSSIAN(R.string.keyboard_russian),
    CZECH(R.string.keyboard_czech),
    POLISH(R.string.keyboard_polish),
    PORTUGUESE(R.string.keyboard_portuguese),
    BRAZILIAN(R.string.keyboard_portuguese_br),
    GREEK(R.string.keyboard_greek),
    TURKISH(R.string.keyboard_turkish),
    HEBREW(R.string.keyboard_hebrew),
    BULGARIAN(R.string.keyboard_bulgarian),
    UKRAINIAN(R.string.keyboard_ukrainian),
    PERSIAN(R.string.keyboard_persian)
}