package com.atharok.btremote.ui.views.keyboard

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.getAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.AdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardModifierKey

@Composable
fun AdvancedKeyboardLayoutView(
    keyboardLanguage: KeyboardLanguage,
    sendKeyboardKeyReport: (bytes: ByteArray) -> Unit,
    showKeyboardBottomSheet: Boolean,
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var advancedKeyboardLayout: AdvancedKeyboardLayout by remember {
        mutableStateOf(getAdvancedKeyboardLayout(keyboardLanguage))
    }

    LaunchedEffect(keyboardLanguage) {
        advancedKeyboardLayout = getAdvancedKeyboardLayout(keyboardLanguage)
    }

    KeyboardModalBottomSheet(
        showKeyboardBottomSheet = showKeyboardBottomSheet,
        onShowKeyboardBottomSheetChanged = onShowKeyboardBottomSheetChanged,
        windowInsets = WindowInsets(0, 0, 0, 0),
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            AdvancedKeyboardLayoutView(
                keyboardLayout = advancedKeyboardLayout,
                sendKeyboardKeyReport = sendKeyboardKeyReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_max))
            )
        }
    }
}

@Composable
private fun AdvancedKeyboardLayoutView(
    keyboardLayout: AdvancedKeyboardLayout,
    sendKeyboardKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var modifierKeyByte: Byte by remember {
        mutableStateOf(0x00)
    }

    var keyByte: Byte by remember {
        mutableStateOf(0x00)
    }

    LaunchedEffect(modifierKeyByte, keyByte) {
        sendKeyboardKeyReport(byteArrayOf(modifierKeyByte, keyByte))
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.sizeIn(
                maxHeight = configuration.screenHeightDp.dp * if(isLandscape) 1f else 0.5f
            ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            keyboardLayout.layout.forEach { keyboardRow ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    keyboardRow.forEach { keyboardKey ->
                        keyboardKey.keyView(
                            { // touchDown
                                if(keyboardKey is TextAdvancedKeyboardModifierKey) {
                                    modifierKeyByte = (modifierKeyByte + it).toByte()
                                } else {
                                    keyByte = it
                                }
                            },
                            { // touchUp
                                if(keyboardKey is TextAdvancedKeyboardModifierKey) {
                                    modifierKeyByte = (modifierKeyByte - it).toByte()
                                } else {
                                    // If 'it' is the last input pressed
                                    if(it == keyByte) {
                                        keyByte = 0x00
                                    }
                                }
                            },
                            Modifier.weight(keyboardKey.weight).padding(dimensionResource(id = R.dimen.padding_min))
                        )
                    }
                }
            }
        }
    }
}