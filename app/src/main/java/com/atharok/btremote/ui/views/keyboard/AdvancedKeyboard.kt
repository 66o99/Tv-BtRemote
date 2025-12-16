package com.atharok.btremote.ui.views.keyboard

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.getAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.AdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardModifierKey
import com.atharok.btremote.ui.theme.dimensionElevation3
import kotlin.jvm.internal.Ref.ByteRef

@Composable
fun AdvancedKeyboardModalBottomSheet(
    keyboardLanguage: KeyboardLanguage,
    sendKeyboardKeyReport: (bytes: ByteArray) -> Unit,
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius)),
    elevation: Dp = dimensionElevation3()
) {
    KeyboardModalBottomSheet(
        onShowKeyboardBottomSheetChanged = onShowKeyboardBottomSheetChanged,
        windowInsets = WindowInsets.safeDrawing,
        modifier = modifier
    ) {
        AdvancedKeyboard(
            keyboardLanguage = keyboardLanguage,
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_large))
                .padding(bottom = dimensionResource(id = R.dimen.padding_max)),
            shape = shape,
            elevation = elevation
        )
    }
}

@Composable
fun AdvancedKeyboard(
    keyboardLanguage: KeyboardLanguage,
    sendKeyboardKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius)),
    elevation: Dp = dimensionElevation3()
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var keyboardLayout: AdvancedKeyboardLayout by remember {
        mutableStateOf(getAdvancedKeyboardLayout(keyboardLanguage))
    }

    val modifierKeyByteRef = remember {
        ByteRef()
    }

    val keyByteRef = remember {
        ByteRef()
    }

    fun handleSendKeyboardKeyReport() {
        sendKeyboardKeyReport(byteArrayOf(modifierKeyByteRef.element, keyByteRef.element))
    }

    LaunchedEffect(keyboardLanguage) {
        keyboardLayout = getAdvancedKeyboardLayout(keyboardLanguage)
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        BoxWithConstraints(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            val containerWidth = maxWidth
            Column(
                modifier = Modifier.sizeIn(
                    maxHeight = configuration.screenHeightDp.dp * if (isLandscape) 1f else 0.5f
                ),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ) {
                keyboardLayout.layout.forEach { keyboardRow ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.Absolute.spacedBy(dimensionResource(id = R.dimen.padding_small))
                    ) {
                        keyboardRow.forEach { keyboardKey ->
                            keyboardKey.keyView(
                                { // touchDown
                                    if (keyboardKey is TextAdvancedKeyboardModifierKey) {
                                        modifierKeyByteRef.element = (modifierKeyByteRef.element + it).toByte()
                                    } else {
                                        keyByteRef.element = it
                                    }
                                    handleSendKeyboardKeyReport()
                                },
                                { // touchUp
                                    if (keyboardKey is TextAdvancedKeyboardModifierKey) {
                                        modifierKeyByteRef.element = (modifierKeyByteRef.element - it).toByte()
                                    } else {
                                        // If 'it' is the last input pressed
                                        if (it == keyByteRef.element) {
                                            keyByteRef.element = 0x00
                                        }
                                    }
                                    handleSendKeyboardKeyReport()
                                },
                                Modifier.then(
                                    if (keyboardKey.weight != 0f) Modifier.weight(keyboardKey.weight)
                                    else Modifier.width(containerWidth.times(keyboardKey.fraction))
                                ),
                                shape,
                                elevation
                            )
                        }
                    }
                }
            }
        }
    }
}