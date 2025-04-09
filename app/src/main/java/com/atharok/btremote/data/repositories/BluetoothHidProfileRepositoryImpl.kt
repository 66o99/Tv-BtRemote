package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.bluetooth.BluetoothHidProfile
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidProfileRepositoryImpl(private val hidProfile: BluetoothHidProfile): BluetoothHidProfileRepository {

    override fun startHidProfile() {
        hidProfile.startBluetoothHidProfile()
    }

    override fun stopHidProfile() {
        hidProfile.stopBluetoothHidProfile()
    }

    override fun isBluetoothServiceStarted(): StateFlow<Boolean> {
        return hidProfile.isBluetoothServiceStarted
    }

    override fun isBluetoothHidProfileRegistered(): StateFlow<Boolean> {
        return hidProfile.isBluetoothHidProfileRegistered
    }

    override fun connectDevice(deviceAddress: String): Boolean {
        return hidProfile.connectDevice(deviceAddress)
    }

    override fun disconnectDevice(): Boolean {
        return hidProfile.disconnectDevice()
    }

    override fun getBluetoothDeviceName(): String? = hidProfile.getBluetoothDeviceName()

    override fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> {
        return hidProfile.deviceHidConnectionState
    }

    override fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return hidProfile.sendReport(id, bytes)
    }

    override fun sendTextReport(text: String, virtualKeyboardLayout: VirtualKeyboardLayout): Boolean {
        return hidProfile.sendTextReport(text, virtualKeyboardLayout)
    }

    override fun getAutoConnectDeviceAddressFlow(): Flow<String> = hidProfile.autoConnectDeviceAddressFlow

    override suspend fun saveAutoConnectDeviceAddress(address: String) {
        hidProfile.saveAutoConnectDeviceAddress(address)
    }
}