package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.common.utils.KEYBOARD_REPORT_ID
import com.atharok.btremote.common.utils.MOUSE_REPORT_ID
import com.atharok.btremote.common.utils.REMOTE_REPORT_ID
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.usecases.RemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class RemoteViewModel(
    private val useCase: RemoteUseCase
): ViewModel() {

    // ---- Settings ----

    val remoteSettingsFlow: Flow<RemoteSettings> = useCase.remoteSettingsFlow

    // ---- Connection ----

    fun disconnectDevice(): Boolean = useCase.disconnectDevice()

    // ---- Send ----

    private fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return useCase.sendReport(id, bytes)
    }

    // Remote
    val sendRemoteReport: (ByteArray) -> Unit = { bytes -> sendReport(REMOTE_REPORT_ID, bytes) }

    // Mouse
    val sendMouseReport: (MouseAction, Float, Float, Float) -> Unit = { input, x, y, wheel ->
        val xInt = x.roundToInt().coerceIn(-127, 127)
        val yInt = y.roundToInt().coerceIn(-127, 127)
        val bytes: ByteArray = byteArrayOf(input.byte, xInt.toByte(), yInt.toByte(), wheel.roundToInt().toByte())
        sendReport(MOUSE_REPORT_ID, bytes)
    }

    // Keyboard
    val sendKeyboardReport: (ByteArray) -> Unit = { bytes -> sendReport(KEYBOARD_REPORT_ID, bytes) }

    // Text (Keyboard)
    private var sendTextJob: Job? = null
    val sendTextReport: (String, VirtualKeyboardLayout, Boolean) -> Unit = { text, virtualKeyboardLayout, shouldSendEnter ->
        if(sendTextJob?.isActive != true) {
            sendTextJob = viewModelScope.launch(Dispatchers.Default) {
                useCase.sendTextReport(text, virtualKeyboardLayout, shouldSendEnter)
            }
        }
    }
}