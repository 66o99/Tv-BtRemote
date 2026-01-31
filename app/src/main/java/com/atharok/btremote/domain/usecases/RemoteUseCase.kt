package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.repositories.BluetoothRepository
import com.atharok.btremote.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class RemoteUseCase(
    private val bluetoothRepository: BluetoothRepository,
    private val dataStoreRepository: DataStoreRepository
) {

    // ---- Settings ----

    val remoteSettingsFlow: Flow<RemoteSettings> get() = dataStoreRepository.remoteSettingsFlow

    // ---- Connection ----

    fun disconnectDevice(): Boolean {
        return bluetoothRepository.disconnectDevice()
    }

    // ---- Send ----

    fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return bluetoothRepository.sendReport(id, bytes)
    }

    suspend fun sendTextReport(
        text: String,
        virtualKeyboardLayout: VirtualKeyboardLayout,
        shouldSendEnter: Boolean
    ): Boolean {
        return bluetoothRepository.sendTextReport(text, virtualKeyboardLayout, shouldSendEnter)
    }
}