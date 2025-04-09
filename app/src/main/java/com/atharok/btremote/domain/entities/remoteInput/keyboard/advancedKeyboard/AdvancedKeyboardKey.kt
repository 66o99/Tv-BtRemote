package com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.ui.views.keyboard.IconAdvancedKeyboardKeyView
import com.atharok.btremote.ui.views.keyboard.TextAdvancedKeyboardKeyView
import com.atharok.btremote.ui.views.keyboard.TextAdvancedKeyboardModifierKeyView

sealed class AdvancedKeyboardKey {
    abstract val byte: Byte
    abstract val weight: Float
    abstract val keyView: @Composable (
        touchDown: (Byte) -> Unit,
        touchUp: (Byte) -> Unit,
        Modifier,
        shape: Shape,
        elevation: Dp
    ) -> Unit
}

data class TextAdvancedKeyboardKey(
    override val byte: Byte,
    override val weight: Float,
    val text: String,
    val textSecondary: String? = null,
    val textTertiary: String? = null
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
        (Byte) -> Unit,
        (Byte) -> Unit,
        Modifier,
        Shape,
        Dp
    ) -> Unit = { touchDown, touchUp, modifier, shape, elevation ->
        TextAdvancedKeyboardKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier,
            shape = shape,
            elevation = elevation
        )
    }
}

data class IconAdvancedKeyboardKey(
    override val byte: Byte,
    override val weight: Float,
    val icon: ImageVector,
    val contentDescription: String? = null
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
            (Byte) -> Unit,
            (Byte) -> Unit,
            Modifier,
            Shape,
            Dp
    ) -> Unit = { touchDown, touchUp, modifier, shape, elevation ->
        IconAdvancedKeyboardKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier,
            shape = shape,
            elevation = elevation
        )
    }
}

data class TextAdvancedKeyboardModifierKey(
    override val byte: Byte,
    override val weight: Float,
    val text: String,
    val textAlign: TextAlign
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
            (Byte) -> Unit,
            (Byte) -> Unit,
            Modifier,
            Shape,
            Dp
    ) -> Unit = { touchDown, touchUp, modifier, shape, elevation ->
        TextAdvancedKeyboardModifierKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier,
            shape = shape,
            elevation = elevation
        )
    }
}