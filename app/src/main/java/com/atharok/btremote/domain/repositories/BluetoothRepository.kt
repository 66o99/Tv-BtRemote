package com.atharok.btremote.domain.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import kotlinx.coroutines.flow.StateFlow

interface BluetoothRepository {

    // ---- BluetoothStatus ----

    fun isBluetoothSupported(): Boolean
    fun isBluetoothEnabled(): Boolean

    // ---- BluetoothScanner ----

    fun getScannedDevices(): SnapshotStateList<DeviceEntity>
    fun registerBluetoothScannerReceiver()
    fun unregisterBluetoothScannerReceiver()
    fun startDiscovery(): Boolean
    fun cancelDiscovery(): Boolean

    // ---- BluetoothLocalData ----

    fun getLocalDeviceName(): String
    fun getBondedDevices(): List<DeviceEntity>
    fun unpairDevice(address: String): Boolean

    // ---- BluetoothHidCore ----

    fun startHidProfile(autoConnectDeviceAddress: String)

    fun stopHidProfile()

    fun isBluetoothServiceRunning(): StateFlow<Boolean>

    fun isBluetoothHidProfileRegistered(): StateFlow<Boolean>

    fun connectDevice(deviceAddress: String): Boolean

    fun disconnectDevice(): Boolean

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState>

    fun sendReport(id: Int, bytes: ByteArray): Boolean

    suspend fun sendTextReport(
        text: String,
        virtualKeyboardLayout: VirtualKeyboardLayout,
        shouldSendEnter: Boolean
    ): Boolean
}