package com.atharok.btremote.ui.views.mouse

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import com.atharok.btremote.ui.components.DefaultElevatedCard
import kotlin.jvm.internal.Ref.FloatRef

@Composable
fun MousePad(
    mouseSpeed: FloatRef,
    updateMouseInput: (input: MouseAction) -> Unit,
    updateTouchPosition: (Float, Float) -> Unit,
    updateWheel: (Float) -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    awaitEachGesture {
                        while (true) {
                            val event = awaitPointerEvent()
                            val inputChanges = event.changes

                            when (inputChanges.size) {

                                // 1 finger
                                1 -> {
                                    val inputChange = inputChanges.first()

                                    moveMouse(
                                        mouseSpeed = mouseSpeed.element,
                                        inputChange = inputChange,
                                        updateTouchPosition = updateTouchPosition
                                    )
                                    doTap(
                                        inputChange = inputChange,
                                        updateMouseInput = updateMouseInput
                                    )
                                }

                                // 2 fingers
                                2 -> {
                                    doWheel(
                                        inputChanges = inputChanges,
                                        updateMouseInput = updateMouseInput,
                                        updateWheel = updateWheel
                                    )
                                }
                            }
                            inputChanges.forEach { it.consume() }
                        }
                    }
                }
        )
    }
}

// ---- Actions ----

private fun moveMouse(
    mouseSpeed: Float,
    inputChange: PointerInputChange,
    updateTouchPosition: (Float, Float) -> Unit
) {
    updateTouchPosition(
        (inputChange.position.x - inputChange.previousPosition.x) * mouseSpeed,
        (inputChange.position.y - inputChange.previousPosition.y) * mouseSpeed
    )
}

private var tapTimestamp: Long = 0L
private fun doTap(
    inputChange: PointerInputChange,
    updateMouseInput: (input: MouseAction) -> Unit
) {
    val currentTime = System.currentTimeMillis()

    when {
        inputChange.changedToDown() -> tapTimestamp = currentTime
        inputChange.changedToUp() -> {
            val position = inputChange.position
            val previousPosition = inputChange.previousPosition
            if(currentTime - tapTimestamp < 200 && position.x == previousPosition.x && position.y == previousPosition.y) {
                updateMouseInput(MouseAction.PAD_TAP)
            }
            tapTimestamp = 0L
        }
    }
}

private fun doWheel(
    inputChanges: List<PointerInputChange>,
    updateMouseInput: (input: MouseAction) -> Unit,
    updateWheel: (Float) -> Unit
) {
    var posY = 0f
    inputChanges.forEach {
        posY += it.position.y - it.previousPosition.y
    }

    when {
        posY != 0f -> updateWheel(posY / 10f)
        else -> updateMouseInput(MouseAction.NONE)
    }

    for (inputChange in inputChanges) {
        if(inputChange.changedToUp()) {
            updateMouseInput(MouseAction.NONE)
            break
        }
    }
}