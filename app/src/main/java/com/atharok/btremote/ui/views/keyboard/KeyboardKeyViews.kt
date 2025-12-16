package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.IconAdvancedKeyboardKey
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardKey
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardModifierKey
import com.atharok.btremote.ui.components.AdaptiveText
import com.atharok.btremote.ui.components.ButtonContentTemplate
import com.atharok.btremote.ui.components.RemoteButtonSurface

@Composable
fun KeyboardKeyView(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    elevation: Dp,
    content: @Composable () -> Unit
) {
    RemoteButtonSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        ButtonContentTemplate(
            touchDown = touchDown,
            touchUp = touchUp,
            shape = shape
        ) {
            content()
        }
    }
}

@Composable
fun TextAdvancedKeyboardKeyView(
    keyboardKey: TextAdvancedKeyboardKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    elevation: Dp
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            keyboardKey.textSecondary?.let {
                AdaptiveText(
                    text = it,
                    percent = 0.8f,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
            } ?: run {
                Spacer(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }

            AdaptiveText(
                text = keyboardKey.text,
                percent = 0.8f,
                modifier = Modifier.fillMaxWidth().weight(1f),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            keyboardKey.textTertiary?.let {
                AdaptiveText(
                    text = it,
                    percent = 0.8f,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            } ?: run {
                Spacer(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
        }
    }
}

@Composable
fun IconAdvancedKeyboardKeyView(
    keyboardKey: IconAdvancedKeyboardKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    elevation: Dp
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            imageVector = keyboardKey.icon,
            contentDescription = keyboardKey.contentDescription,
            modifier = Modifier.fillMaxSize(0.35f)
        )
    }
}

@Composable
fun TextAdvancedKeyboardModifierKeyView(
    keyboardKey: TextAdvancedKeyboardModifierKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    elevation: Dp
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            contentAlignment = Alignment.Center
        ) {
            AdaptiveText(
                text = keyboardKey.text,
                percent = 0.8f,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.333f),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = keyboardKey.textAlign
            )
        }
    }
}