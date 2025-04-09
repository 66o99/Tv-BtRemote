package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidUseCase(private val repository: BluetoothHidProfileRepository) {

    fun isBluetoothServiceStarted(): StateFlow<Boolean> {
        return repository.isBluetoothServiceStarted()
    }

    fun isBluetoothHidProfileRegistered(): StateFlow<Boolean> {
        return repository.isBluetoothHidProfileRegistered()
    }

    fun connectDevice(deviceAddress: String): Boolean {
        return repository.connectDevice(deviceAddress)
    }

    fun disconnectDevice(): Boolean {
        return repository.disconnectDevice()
    }

    fun getBluetoothDeviceName(): String? = repository.getBluetoothDeviceName()

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> {
        return repository.getDeviceHidConnectionState()
    }

    fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return repository.sendReport(id, bytes)
    }

    fun sendTextReport(text: String, virtualKeyboardLayout: VirtualKeyboardLayout): Boolean {
        return repository.sendTextReport(text, virtualKeyboardLayout)
    }

    fun getAutoConnectDeviceAddressFlow(): Flow<String> = repository.getAutoConnectDeviceAddressFlow()
    suspend fun saveAutoConnectDeviceAddress(address: String) {
        repository.saveAutoConnectDeviceAddress(address)
    }
}