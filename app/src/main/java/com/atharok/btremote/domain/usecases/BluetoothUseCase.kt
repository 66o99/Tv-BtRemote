package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.repositories.BluetoothRepository

class BluetoothUseCase(private val bluetoothRepository: BluetoothRepository) {

    // ---- Bluetooth Initialization ----

    fun isBluetoothSupported(): Boolean = bluetoothRepository.isBluetoothSupported()

    fun isBluetoothEnabled(): Boolean = bluetoothRepository.isBluetoothEnabled()

    // ---- About local device ----

    fun getLocalDeviceName(): String = bluetoothRepository.getLocalDeviceName()

    // ---- Bluetooth permissions ----

    fun getBluetoothPermissions(): Array<String> = bluetoothRepository.getBluetoothPermissions()

    fun areBluetoothPermissionsGranted(): Boolean = bluetoothRepository.areBluetoothPermissionsGranted()

    fun getBluetoothScanningPermissions(): Array<String> = bluetoothRepository.getBluetoothScanningPermissions()

    fun areBluetoothScanningPermissionsGranted(): Boolean = bluetoothRepository.areBluetoothScanningPermissionsGranted()

    // ---- Get Bonded Devices ----

    fun getBondedDevices(): List<DeviceEntity> = bluetoothRepository.getBondedDevices()

    // ---- Discover Devices ----

    fun startDiscovery(): Boolean = bluetoothRepository.startDiscovery()

    fun cancelDiscovery(): Boolean = bluetoothRepository.cancelDiscovery()

    fun unpairDevice(address: String): Boolean = bluetoothRepository.unpairDevice(address)
}