package com.atharok.btremote.ui.views.mouse

import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.jvm.internal.Ref.FloatRef
import kotlin.jvm.internal.Ref.LongRef
import kotlin.jvm.internal.Ref.ObjectRef

@Composable
fun MousePadLayout(
    mouseSpeed: Float,
    scrollSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    useGyroscope: Boolean,
    sendMouseInput: (MouseAction, Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val mouseActionRef = remember {
        val ref = ObjectRef<MouseAction>()
        ref.element = MouseAction.NONE
        ref
    }
    val mouseXRef = remember {
        FloatRef()
    }
    val mouseYRef = remember {
        FloatRef()
    }
    val mouseWheelRef = remember {
        FloatRef()
    }

    fun processMouse(
        mouseAction: MouseAction? = null,
        mouseX: Float? = null,
        mouseY: Float? = null,
        mouseWheel: Float? = null
    ) {
        mouseActionRef.element = mouseAction ?: mouseActionRef.element
        mouseXRef.element = mouseX ?: mouseXRef.element
        mouseYRef.element = mouseY ?: mouseYRef.element
        mouseWheelRef.element = mouseWheel ?: mouseWheelRef.element
        sendMouseInput(
            mouseActionRef.element,
            mouseXRef.element,
            mouseYRef.element,
            mouseWheelRef.element
        )
    }

    if (useGyroscope) {
        MouseGyroscope(
            mouseSpeed = mouseSpeed,
            onMousePositionChange = { x: Float, y: Float ->
                processMouse(mouseX = x, mouseY = y)
            }
        )
    }

    BoxWithConstraints {
        val containerWidth = maxWidth
        Column(modifier) {
            Row(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(bottom = dimensionResource(id = R.dimen.padding_min))
            ) {
                MousePad(
                    mouseSpeed = mouseSpeed,
                    scrollSpeed = scrollSpeed,
                    updateMouseInput = {
                        processMouse(mouseAction = it)
                    },
                    updateTouchPosition = { x: Float, y: Float ->
                        processMouse(mouseX = x, mouseY = y)
                    },
                    updateWheel = { wheel: Float ->
                        processMouse(mouseWheel = wheel * (if (shouldInvertMouseScrollingDirection) -1f else 1f))
                    },
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.card_corner_radius),
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = dimensionResource(id = R.dimen.padding_min))
                )

                ScrollMouseButtonsLayout(
                    scrollSpeed = scrollSpeed,
                    onMouseScrollingChange = {
                        processMouse(mouseWheel = it)
                    },
                    modifier = Modifier
                        .width(containerWidth.times(0.15f).coerceAtMost(75.dp))
                        .padding(start = dimensionResource(id = R.dimen.padding_min))
                )
            }

            MouseButtonsLayout(
                onMouseActionChange = {
                    processMouse(mouseAction = it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .padding(top = dimensionResource(id = R.dimen.padding_min))
            )
        }
    }
}

// ---- Mouse buttons ----

@Composable
private fun MouseButtonsLayout(
    onMouseActionChange: (MouseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current

    if (layoutDirection == LayoutDirection.Rtl) {
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
    scrollSpeed: Float,
    onMouseScrollingChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentScrollSpeed by rememberUpdatedState(scrollSpeed)
    val coroutineScope = rememberCoroutineScope()
    val mouseWheelRef = remember {
        FloatRef()
    }
    val mouseWheelJobRef = remember {
        ObjectRef<Job?>()
    }
    val lastMouseWheelMillisRef = remember {
        val ref = LongRef()
        ref.element = SystemClock.uptimeMillis()
        ref
    }

    fun handleMouseWheelChange(value: Float) {
        mouseWheelRef.element = value
        lastMouseWheelMillisRef.element = SystemClock.uptimeMillis()
        onMouseScrollingChange(mouseWheelRef.element)
        onMouseScrollingChange(0f)

        mouseWheelJobRef.element?.cancel()
        mouseWheelJobRef.element = if (value == 0f) null else coroutineScope.launch {
            val stepMillis = (50f / currentScrollSpeed).toInt()
            while (isActive) {
                val deltaMillis = SystemClock.uptimeMillis() - lastMouseWheelMillisRef.element
                if (deltaMillis >= stepMillis) {
                    lastMouseWheelMillisRef.element = SystemClock.uptimeMillis()
                    onMouseScrollingChange(mouseWheelRef.element)
                    onMouseScrollingChange(0f)
                } else {
                    delay(stepMillis - deltaMillis)
                }
            }
        }
    }

    Column(modifier = modifier) {
        ScrollMouseButton(
            touchDown = { handleMouseWheelChange(1f) },
            touchUp = { handleMouseWheelChange(0f) },
            image = AppIcons.MouseScrollUp,
            contentDescription = stringResource(id = R.string.mouse_wheel_up),
            shape = RoundedCornerShape(topEnd = dimensionResource(id = R.dimen.card_corner_radius)),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_min))
        )

        ScrollMouseButton(
            touchDown = { handleMouseWheelChange(-1f) },
            touchUp = { handleMouseWheelChange(0f) },
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