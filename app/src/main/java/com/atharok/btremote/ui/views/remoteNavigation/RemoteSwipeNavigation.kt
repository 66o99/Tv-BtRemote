package com.atharok.btremote.ui.views.remoteNavigation

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entities.remoteInput.RemoteInput
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey
import com.atharok.btremote.ui.components.DefaultElevatedCard
import kotlin.math.abs
import kotlin.math.sqrt

enum class SwipeDirection(val bytes: ByteArray) {
    NONE(REMOTE_INPUT_NONE),
    UP(RemoteInput.REMOTE_INPUT_MENU_UP),
    LEFT(RemoteInput.REMOTE_INPUT_MENU_LEFT),
    RIGHT(RemoteInput.REMOTE_INPUT_MENU_RIGHT),
    DOWN(RemoteInput.REMOTE_INPUT_MENU_DOWN),
    PICK(RemoteInput.REMOTE_INPUT_MENU_PICK)
}

private const val SWIPE_PAD_DETECTION_DISTANCE = 5

@Composable
fun RemoteSwipeNavigation(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    useEnterForSelection: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))
) {
    val haptic = LocalHapticFeedback.current

    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDirection(
                        onDirectionDetected = {
                            if(it == SwipeDirection.PICK) {
                                if(useEnterForSelection) {
                                    sendKeyboardKeyReport(byteArrayOf(0x00, KeyboardKey.KEY_ENTER.byte))
                                    sendKeyboardKeyReport(SwipeDirection.NONE.bytes)
                                } else {
                                    sendRemoteKeyReport(it.bytes)
                                    sendRemoteKeyReport(SwipeDirection.NONE.bytes)
                                }
                            } else {
                                sendRemoteKeyReport(it.bytes)
                            }
                            if(it != SwipeDirection.NONE) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            }
                        }
                    )
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
    onDirectionDetected: (SwipeDirection) -> Unit
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
                                        onDirectionDetected(SwipeDirection.RIGHT)
                                    } else {
                                        onDirectionDetected(SwipeDirection.LEFT)
                                    }
                                } else {
                                    if (deltaY > 0) {
                                        onDirectionDetected(SwipeDirection.DOWN)
                                    } else {
                                        onDirectionDetected(SwipeDirection.UP)
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
                        onDirectionDetected(SwipeDirection.PICK)
                    }
                    !position.pressed && isTouching && distance >= SWIPE_PAD_DETECTION_DISTANCE -> {
                        initialX = null
                        initialY = null
                        isTouching = false
                        distance = 0f
                        onDirectionDetected(SwipeDirection.NONE)
                    }
                }
                position.consume()
            }
        }
    }
}