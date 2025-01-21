package com.atharok.btremote.domain.usecases

import com.atharok.btremote.common.utils.REMOTE_REPORT_ID
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidServiceUseCase(private val repository: BluetoothHidProfileRepository) {

    fun startHidProfile() {
        repository.startHidProfile()
    }

    fun stopHidProfile() {
        repository.stopHidProfile()
    }

    fun disconnectDevice(): Boolean {
        return repository.disconnectDevice()
    }

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> {
        return repository.getDeviceHidConnectionState()
    }

    fun sendRemoteReport(bytes: ByteArray): Boolean {
        return repository.sendReport(REMOTE_REPORT_ID, bytes)
    }
}