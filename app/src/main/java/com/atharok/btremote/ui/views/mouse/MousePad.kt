package com.atharok.btremote.ui.views.mouse

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import com.atharok.btremote.ui.components.DefaultElevatedCard
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.jvm.internal.Ref.BooleanRef
import kotlin.jvm.internal.Ref.FloatRef
import kotlin.jvm.internal.Ref.LongRef
import kotlin.jvm.internal.Ref.ObjectRef
import kotlin.math.pow
import kotlin.math.truncate

@Composable
fun MousePad(
    mouseSpeed: Float,
    scrollSpeed: Float,
    updateMouseInput: (input: MouseAction) -> Unit,
    updateTouchPosition: (Float, Float) -> Unit,
    updateWheel: (Float) -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    val currentMouseSpeed by rememberUpdatedState(mouseSpeed)
    val currentScrollSpeed by rememberUpdatedState(scrollSpeed)
    val haptic = LocalHapticFeedback.current
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val initialInputChangesRef = remember {
        val ref = ObjectRef<MutableMap<PointerId, PointerInputChange>>()
        ref.element = mutableMapOf()
        ref
    }
    val tapJobRef = remember {
        ObjectRef<Job?>()
    }
    val longTapJobRef = remember {
        ObjectRef<Job?>()
    }
    val tapPositionChangedRef = remember {
        BooleanRef()
    }
    val lastMultiTapMillisRef = remember {
        LongRef()
    }
    val accumulatedScrollDistanceRef = remember {
        FloatRef()
    }

    fun registerInitialInputChanges(inputChanges: List<PointerInputChange>) {
        for (inputChange in inputChanges) {
            if (inputChange.changedToDown()) {
                initialInputChangesRef.element[inputChange.id] = inputChange.copy()
            }
        }
    }

    fun unregisterInitialInputChanges(inputChanges: List<PointerInputChange>) {
        for (inputChange in inputChanges) {
            if (inputChange.changedToUp()) {
                initialInputChangesRef.element.remove(inputChange.id)
            }
        }
    }

    fun positionChanged(inputChange: PointerInputChange): Boolean {
        val touchSlopSquared = 10f.pow(2f)
        val initialInputChange = initialInputChangesRef.element[inputChange.id] ?: return true
        val inputChangeDistanceSquared = with(density) {
            (inputChange.position - initialInputChange.position).getDistanceSquared().toDp().value
        }
        return inputChangeDistanceSquared > touchSlopSquared
    }

    fun doTap(inputChanges: List<PointerInputChange>) {
        if (inputChanges.size != 1 || positionChanged(inputChanges.first())) {
            tapPositionChangedRef.element = true
            lastMultiTapMillisRef.element = 0L
            val longTapJob = longTapJobRef.element
            if (longTapJob?.isActive == true) {
                longTapJobRef.element = null
                longTapJob.cancel()
            }
            if (inputChanges.size != 1) {
                return
            }
        }

        val tapTimeout = 200L
        val longTapTimeout = 400L
        val inputChange = inputChanges.first()
        val initialInputChange = initialInputChangesRef.element[inputChange.id]
        val inputChangeDuration = when {
            initialInputChange == null -> Long.MAX_VALUE
            else -> inputChange.uptimeMillis - initialInputChange.uptimeMillis
        }
        if (
            inputChange.changedToDown() &&
            lastMultiTapMillisRef.element != 0L &&
            inputChange.uptimeMillis - lastMultiTapMillisRef.element > tapTimeout
        ) {
            lastMultiTapMillisRef.element = 0L
            tapJobRef.element = null
        }

        val tapJob = tapJobRef.element
        val longTapJob = longTapJobRef.element
        val startedMultiTap = lastMultiTapMillisRef.element != 0L

        if (inputChange.changedToDown() && !tapPositionChangedRef.element) {
            if (tapJob == null) when {
                longTapJob == null -> {
                    longTapJobRef.element = coroutineScope.launch {
                        delay(longTapTimeout)
                        if (isActive) {
                            updateMouseInput(MouseAction.MOUSE_CLICK_RIGHT)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    }
                }
            } else when {
                !startedMultiTap -> {
                    tapJob.cancel()
                    updateMouseInput(MouseAction.MOUSE_CLICK_LEFT)
                    haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
                }
            }
        }

        if (inputChange.changedToUp()) {
            if (tapJob == null) when {
                !tapPositionChangedRef.element && inputChangeDuration <= tapTimeout -> {
                    if (longTapJob?.isActive == true) {
                        longTapJobRef.element = null
                        longTapJob.cancel()
                    }
                    tapJobRef.element = coroutineScope.launch {
                        delay(tapTimeout)
                        if (isActive) {
                            tapJobRef.element = null
                            updateMouseInput(MouseAction.MOUSE_CLICK_LEFT)
                            updateMouseInput(MouseAction.NONE)
                            haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
                        }
                    }
                }

                longTapJob != null -> {
                    longTapJobRef.element = null
                    if (longTapJob.isActive) {
                        longTapJob.cancel()
                    } else if (longTapJob.isCompleted && !longTapJob.isCancelled) {
                        updateMouseInput(MouseAction.NONE)
                    }
                }
            } else when {
                !tapPositionChangedRef.element && inputChangeDuration <= tapTimeout -> {
                    lastMultiTapMillisRef.element = inputChange.uptimeMillis
                    if (!startedMultiTap) updateMouseInput(MouseAction.NONE)
                    updateMouseInput(MouseAction.MOUSE_CLICK_LEFT)
                    updateMouseInput(MouseAction.NONE)
                    haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
                }

                else -> {
                    lastMultiTapMillisRef.element = 0L
                    tapJobRef.element = null
                    if (!startedMultiTap) updateMouseInput(MouseAction.NONE)
                }
            }
            tapPositionChangedRef.element = false
        }
    }

    fun doMove(inputChanges: List<PointerInputChange>) {
        if (inputChanges.size != 1 || !positionChanged(inputChanges.first())) {
            return
        }

        val baseFactor = 3f
        val inputChange = inputChanges.first()
        val positionChange = inputChange.positionChange().times(baseFactor)
        val deltaX = with(density) { positionChange.x.toDp().value * currentMouseSpeed }
        val deltaY = with(density) { positionChange.y.toDp().value * currentMouseSpeed }

        updateTouchPosition(deltaX, deltaY)
        updateTouchPosition(0f, 0f)
    }

    fun doWheel(inputChanges: List<PointerInputChange>) {
        if (inputChanges.size != 2) {
            return
        }

        val positionChangeY =
            inputChanges.fold(0f) { sum, it -> sum + it.positionChange().y } / inputChanges.size
        val deltaY = with(density) { positionChangeY.toDp().value * currentScrollSpeed }
        accumulatedScrollDistanceRef.element += deltaY

        val stepSize = 5f
        val steps = truncate(accumulatedScrollDistanceRef.element / stepSize)
        accumulatedScrollDistanceRef.element -= steps * stepSize

        updateTouchPosition(0f, 0f)
        updateWheel(steps)
        updateWheel(0f)
    }

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
                            registerInitialInputChanges(inputChanges)
                            doTap(inputChanges)
                            doMove(inputChanges)
                            doWheel(inputChanges)
                            unregisterInitialInputChanges(inputChanges)
                            inputChanges.forEach { it.consume() }
                        }
                    }
                }
        )
    }
}