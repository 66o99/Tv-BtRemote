package com.atharok.btremote.ui.views.remoteNavigation

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.DefaultElevatedCard
import kotlin.math.abs
import kotlin.math.sqrt

enum class SwipeAction() {
    NONE,
    UP,
    LEFT,
    RIGHT,
    DOWN,
    PICK
}

private const val SWIPE_PAD_DETECTION_DISTANCE = 5

@Composable
fun RemoteSwipeNavigation(
    upTouchDown: () -> Unit,
    downTouchDown: () -> Unit,
    leftTouchDown: () -> Unit,
    rightTouchDown: () -> Unit,
    pickTouchDown: () -> Unit,
    directionTouchUp: () -> Unit,
    pickTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))
) {
    val haptic = LocalHapticFeedback.current

    var action: SwipeAction by remember { mutableStateOf(SwipeAction.NONE) }

    LaunchedEffect(action) {
        when(action) {
            SwipeAction.NONE -> {
                directionTouchUp()
            }
            SwipeAction.UP -> {
                upTouchDown()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            SwipeAction.LEFT -> {
                leftTouchDown()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            SwipeAction.RIGHT -> {
                rightTouchDown()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            SwipeAction.DOWN -> {
                downTouchDown()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
            SwipeAction.PICK -> {
                pickTouchDown()
                pickTouchUp()
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
        }
    }

    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDirection(onSwipeActionDetected = { action = it })
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = AppIcons.Gesture,
                contentDescription = stringResource(id = R.string.touchpad_description),
                modifier = Modifier.fillMaxSize(0.2f),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
        }
    }
}

// ---- Actions ----

suspend fun PointerInputScope.detectDirection(
    onSwipeActionDetected: (SwipeAction) -> Unit
) {
    awaitEachGesture {
        var initialX: Float? = null
        var initialY: Float? = null
        var isTouching = false
        var distance = 0f
        while (true) {
            val event = awaitPointerEvent()
            val position = event.changes.firstOrNull()
            if(position != null) {
                when {
                    position.pressed && distance < SWIPE_PAD_DETECTION_DISTANCE -> {
                        isTouching = true
                        if (initialX == null || initialY == null) {
                            initialX = position.position.x
                            initialY = position.position.y
                        } else {
                            val deltaX = position.position.x - initialX
                            val deltaY = position.position.y - initialY
                            distance = sqrt(deltaX * deltaX + deltaY * deltaY)

                            if(distance >= SWIPE_PAD_DETECTION_DISTANCE) {
                                if (abs(deltaX) > abs(deltaY)) {
                                    if (deltaX > 0) {
                                        onSwipeActionDetected(SwipeAction.RIGHT)
                                    } else {
                                        onSwipeActionDetected(SwipeAction.LEFT)
                                    }
                                } else {
                                    if (deltaY > 0) {
                                        onSwipeActionDetected(SwipeAction.DOWN)
                                    } else {
                                        onSwipeActionDetected(SwipeAction.UP)
                                    }
                                }
                            }
                        }
                    }
                    !position.pressed && isTouching && distance < SWIPE_PAD_DETECTION_DISTANCE -> {
                        initialX = null
                        initialY = null
                        isTouching = false
                        distance = 0f
                        onSwipeActionDetected(SwipeAction.PICK)
                    }
                    !position.pressed && isTouching && distance >= SWIPE_PAD_DETECTION_DISTANCE -> {
                        initialX = null
                        initialY = null
                        isTouching = false
                        distance = 0f
                        onSwipeActionDetected(SwipeAction.NONE)
                    }
                }
                position.consume()
            }
        }
    }
}