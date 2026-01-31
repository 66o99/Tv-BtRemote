package com.atharok.btremote.data.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.atharok.btremote.data.bluetooth.BluetoothHidCore
import com.atharok.btremote.data.bluetooth.BluetoothLocalData
import com.atharok.btremote.data.bluetooth.BluetoothScanner
import com.atharok.btremote.data.bluetooth.BluetoothStatus
import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothRepositoryImpl(
    private val bluetoothStatus: BluetoothStatus,
    private val bluetoothScanner: BluetoothScanner,
    private val bluetoothLocalData: BluetoothLocalData,
    private val bluetoothHidCore: BluetoothHidCore
): BluetoothRepository {

    // ---- BluetoothStatus ----

    override fun isBluetoothSupported(): Boolean = bluetoothStatus.isBluetoothSupported()

    override fun isBluetoothEnabled(): Boolean = bluetoothStatus.isBluetoothEnabled()

    // ---- BluetoothScanner ----

    override fun getScannedDevices(): SnapshotStateList<DeviceEntity> = bluetoothScanner.scannedDevices

    override fun registerBluetoothScannerReceiver() {
        bluetoothScanner.registerBluetoothScannerReceiver()
    }

    override fun unregisterBluetoothScannerReceiver() {
        bluetoothScanner.unregisterBluetoothScannerReceiver()
    }

    override fun startDiscovery(): Boolean = bluetoothScanner.startDiscoveryDevices()

    override fun cancelDiscovery(): Boolean = bluetoothScanner.cancelDiscoveryDevices()

    // ---- BluetoothLocalData ----

    override fun getLocalDeviceName(): String = bluetoothLocalData.getLocalDeviceName()

    override fun getBondedDevices(): List<DeviceEntity> = bluetoothLocalData.getBondedDevices()

    override fun unpairDevice(address: String): Boolean = bluetoothLocalData.unpairDevice(address)

    // ---- BluetoothHidCore ----

    override fun startHidProfile(autoConnectDeviceAddress: String) {
        bluetoothHidCore.startBluetoothHidProfile(autoConnectDeviceAddress)
    }

    override fun stopHidProfile() {
        bluetoothHidCore.stopBluetoothHidProfile()
    }

    override fun isBluetoothServiceRunning(): StateFlow<Boolean> {
        return bluetoothHidCore.isBluetoothServiceRunning
    }

    override fun isBluetoothHidProfileRegistered(): StateFlow<Boolean> {
        return bluetoothHidCore.isBluetoothHidProfileRegistered
    }

    override fun connectDevice(deviceAddress: String): Boolean {
        return bluetoothHidCore.connectDevice(deviceAddress)
    }

    override fun disconnectDevice(): Boolean {
        return bluetoothHidCore.disconnectDevice()
    }

    override fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> {
        return bluetoothHidCore.deviceHidConnectionState
    }

    override fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return bluetoothHidCore.sendReport(id, bytes)
    }

    override suspend fun sendTextReport(
        text: String,
        virtualKeyboardLayout: VirtualKeyboardLayout,
        shouldSendEnter: Boolean
    ): Boolean {
        return bluetoothHidCore.sendTextReport(text, virtualKeyboardLayout, shouldSendEnter)
    }
}