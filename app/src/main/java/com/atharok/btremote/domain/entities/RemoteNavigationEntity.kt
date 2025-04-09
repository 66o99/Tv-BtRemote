package com.atharok.btremote.domain.entities

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class RemoteNavigationEntity(@StringRes val type: Int, @StringRes val description: Int) {
    D_PAD(R.string.d_pad, R.string.d_pad_description),
    TOUCHPAD(R.string.touchpad, R.string.touchpad_description)
}