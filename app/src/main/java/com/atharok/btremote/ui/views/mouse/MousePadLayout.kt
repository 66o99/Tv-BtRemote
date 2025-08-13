package com.atharok.btremote.ui.views.mouse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import kotlin.jvm.internal.Ref.BooleanRef
import kotlin.jvm.internal.Ref.FloatRef

private data class MouseScrolling(
    val mouseX: Float = 0f,
    val mouseY: Float = 0f,
    val mouseWheel: Float = 0f,
)

@Composable
fun MousePadLayout(
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    useGyroscope: Boolean,
    sendMouseInput: (MouseAction, Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val mouseAction: MutableState<MouseAction> = remember { mutableStateOf(MouseAction.NONE) }
    val mouseScrolling: MutableState<MouseScrolling> = remember { mutableStateOf(MouseScrolling()) }

    val mouseSpeedRef = remember { FloatRef() }
    mouseSpeedRef.element = mouseSpeed

    val shouldInvertMouseScrollingDirectionRef = remember { BooleanRef() }
    shouldInvertMouseScrollingDirectionRef.element = shouldInvertMouseScrollingDirection

    LaunchedEffect(mouseAction.value, mouseScrolling.value) {
        sendMouseInput(mouseAction.value, mouseScrolling.value.mouseX, mouseScrolling.value.mouseY, mouseScrolling.value.mouseWheel)
        if (mouseAction.value == MouseAction.PAD_TAP) {
            mouseAction.value = MouseAction.NONE
        }
    }

    if(useGyroscope) {
        MouseGyroscope(
            mouseSpeed = mouseSpeed,
            onMousePositionChange = { x: Float, y: Float ->
                mouseScrolling.value = MouseScrolling(mouseX = x, mouseY = y)
            }
        )
    }

    Column(modifier) {
        Row(
            modifier = Modifier
                .weight(0.8f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_min))
        ) {
            MousePad(
                mouseSpeed = mouseSpeedRef,
                updateMouseInput = {
                    mouseAction.value = it
                },
                updateTouchPosition = { x: Float, y: Float ->
                    mouseScrolling.value = MouseScrolling(mouseX = x, mouseY = y)
                },
                updateWheel = { wheel: Float ->
                    mouseScrolling.value = MouseScrolling(
                        mouseWheel = wheel * if(shouldInvertMouseScrollingDirectionRef.element) -1f else 1f
                    )
                },
                shape = RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.card_corner_radius),
                    topEnd = 0.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .weight(0.85f)
                    .padding(end = dimensionResource(id = R.dimen.padding_min))
            )

            ScrollMouseButtonsLayout(
                mouseScrolling = mouseScrolling.value,
                onMouseScrollingChange = {
                    mouseScrolling.value = it
                },
                modifier = Modifier
                    .weight(0.15f)
                    .padding(start = dimensionResource(id = R.dimen.padding_min))
            )
        }

        MouseButtonsLayout(
            onMouseActionChange = {
                mouseAction.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .padding(top = dimensionResource(id = R.dimen.padding_min))
        )
    }
}

// ---- Mouse buttons ----

@Composable
private fun MouseButtonsLayout(
    onMouseActionChange: (MouseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current

    if(layoutDirection == LayoutDirection.Rtl) {
        MouseButtonsLayoutRTL(onMouseActionChange, modifier)
    } else {
        MouseButtonsLayoutLTR(onMouseActionChange, modifier)
    }
}

@Composable
private fun MouseButtonsLayoutLTR(
    onMouseActionChange: (MouseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Absolute.Left) {

        // Start
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_LEFT) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(end = dimensionResource(id = R.dimen.padding_min))
        )

        // Center
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_MIDDLE) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RectangleShape,
            modifier = Modifier
                .weight(0.24f)
                .fillMaxHeight()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_min),
                    end = dimensionResource(id = R.dimen.padding_min)
                )
        )

        // End
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_RIGHT) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = dimensionResource(id = R.dimen.card_corner_radius),
                bottomStart = 0.dp
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(start = dimensionResource(id = R.dimen.padding_min))
        )
    }
}

@Composable
private fun MouseButtonsLayoutRTL(
    onMouseActionChange: (MouseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Absolute.Left) {

        // Start
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_LEFT) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = dimensionResource(id = R.dimen.card_corner_radius),
                bottomStart = 0.dp
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(start = dimensionResource(id = R.dimen.padding_min))
        )

        // Center
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_MIDDLE) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RectangleShape,
            modifier = Modifier
                .weight(0.24f)
                .fillMaxHeight()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_min),
                    end = dimensionResource(id = R.dimen.padding_min)
                )
        )

        // End
        MouseButton(
            touchDown = { onMouseActionChange(MouseAction.MOUSE_CLICK_RIGHT) },
            touchUp = { onMouseActionChange(MouseAction.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(end = dimensionResource(id = R.dimen.padding_min))
        )
    }
}

// ---- Scroll Up/Down buttons ----

@Composable
private fun ScrollMouseButtonsLayout(
    mouseScrolling: MouseScrolling,
    onMouseScrollingChange: (MouseScrolling) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ScrollMouseButton(
            touchDown = {
                onMouseScrollingChange(
                    MouseScrolling(
                        mouseX = mouseScrolling.mouseX,
                        mouseY = mouseScrolling.mouseY,
                        mouseWheel = 1f
                    )
                )
            },
            touchUp = {
                onMouseScrollingChange(
                    MouseScrolling(
                        mouseX = mouseScrolling.mouseX,
                        mouseY = mouseScrolling.mouseY,
                        mouseWheel = 0f
                    )
                )
            },
            image = AppIcons.MouseScrollUp,
            contentDescription = stringResource(id = R.string.mouse_wheel_up),
            shape = RoundedCornerShape(topEnd = dimensionResource(id = R.dimen.card_corner_radius)),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_min))
        )

        ScrollMouseButton(
            touchDown = {
                onMouseScrollingChange(
                    MouseScrolling(
                        mouseX = mouseScrolling.mouseX,
                        mouseY = mouseScrolling.mouseY,
                        mouseWheel = -1f
                    )
                )
            },
            touchUp = {
                onMouseScrollingChange(
                    MouseScrolling(
                        mouseX = mouseScrolling.mouseX,
                        mouseY = mouseScrolling.mouseY,
                        mouseWheel = 0f
                    )
                )
            },
            image = AppIcons.MouseScrollDown,
            contentDescription = stringResource(id = R.string.mouse_wheel_down),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(top = dimensionResource(id = R.dimen.padding_min))
        )
    }
}