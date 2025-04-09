package com.atharok.btremote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atharok.btremote.common.extensions.capitalizeFirstChar
import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.usecases.BluetoothUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class BluetoothViewModel(
    private val useCase: BluetoothUseCase
): ViewModel() {

    // ---- Bluetooth Initialization ----

    val isBluetoothSupported: Boolean = useCase.isBluetoothSupported()
    fun isBluetoothEnable(): Boolean = useCase.isBluetoothEnabled()

    // ---- About local device ----

    fun getLocalDeviceName(): String = useCase.getLocalDeviceName()

    // ---- Bluetooth permissions ----

    fun getBluetoothPermissions(): Array<String> = useCase.getBluetoothPermissions()

    fun areBluetoothPermissionsGranted(): Boolean = useCase.areBluetoothPermissionsGranted()

    fun getBluetoothScanningPermissions(): Array<String> = useCase.getBluetoothScanningPermissions()

    fun areBluetoothScanningPermissionsGranted(): Boolean = useCase.areBluetoothScanningPermissionsGranted()

    // ---- Get Bonded Devices ----

    private val _devicesEntity: MutableStateFlow<List<DeviceEntity>> = MutableStateFlow(listOf())
    val devicesEntityObserver: StateFlow<List<DeviceEntity>> = _devicesEntity

    fun findBondedDevices(
        context: CoroutineContext = EmptyCoroutineContext
    ) = viewModelScope.launch(context = context) {
        _devicesEntity.value = useCase.getBondedDevices().sortedBy { it.name.capitalizeFirstChar() }
    }

    // ---- Discover Devices ----

    private val _isDiscovering: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDiscovering: StateFlow<Boolean> = _isDiscovering

    private var startDiscoveryJob: Job? = null

    fun startDiscovery() {
        startDiscoveryJob?.cancel()
        useCase.cancelDiscovery()
        useCase.startDiscovery()
        _isDiscovering.value = true
        startDiscoveryJob = viewModelScope.launch {
            delay(12000L)
            cancelDiscovery()
        }
    }

    fun cancelDiscovery() {
        useCase.cancelDiscovery()
        _isDiscovering.value = false
    }

    // ---- Unpair device ----

    fun unpairDevice(address: String): Boolean {
        val success = useCase.unpairDevice(address)
        if(success) {
            runBlocking { delay(200L) } // -> Small delay to allow the system to complete updating the device removal.
            findBondedDevices(context = Dispatchers.Main)
        }
        return success
    }
}