package com.atharok.btremote.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.ui.theme.dimensionElevation1

@Composable
fun StatefulRemoteButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    content: @Composable (interactionSource: MutableInteractionSource) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    touchDown()
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                is PressInteraction.Release -> touchUp()
                is PressInteraction.Cancel -> touchUp()
            }
        }
    }

    content(interactionSource)
}

@Composable
fun ButtonContentTemplate(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    shape: Shape,
    content: @Composable () -> Unit
) {
    StatefulRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .clipToBounds()
                .clickable(
                    interactionSource = it,
                    indication = LocalIndication.current,
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun RemoteButtonSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation1(),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        tonalElevation = elevation,
        shadowElevation = dimensionElevation1()
    ) {
        content()
    }
}