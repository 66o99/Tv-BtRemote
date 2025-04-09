package com.atharok.btremote.domain.entities

import android.bluetooth.BluetoothClass.Device
import androidx.compose.ui.graphics.vector.ImageVector
import com.atharok.btremote.common.utils.AppIcons

data class DeviceEntity(
    val name: String,
    val macAddress: String,
    private val category: Int
) {
    val imageVector: ImageVector = when(category) {
        Device.Major.COMPUTER -> AppIcons.Computer
        Device.Major.PHONE -> AppIcons.Phone
        Device.Major.NETWORKING -> AppIcons.Networking
        Device.Major.AUDIO_VIDEO -> AppIcons.AudioVideo
        Device.Major.PERIPHERAL -> AppIcons.Peripheral
        Device.Major.IMAGING -> AppIcons.Imaging
        Device.Major.WEARABLE -> AppIcons.Wearable
        Device.Major.TOY -> AppIcons.Toy
        Device.Major.HEALTH -> AppIcons.Health
        Device.Major.UNCATEGORIZED -> AppIcons.Uncategorized
        else -> AppIcons.Bluetooth
    }
}
