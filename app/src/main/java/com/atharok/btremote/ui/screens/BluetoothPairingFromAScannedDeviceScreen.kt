package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.extensions.parcelable
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.CheckMultiplePermissions
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.components.RefreshAction
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.SystemBroadcastReceiver
import com.atharok.btremote.ui.components.TextNormalSecondary
import com.atharok.btremote.ui.views.BluetoothScanningScreenHelpModalBottomSheet
import com.atharok.btremote.ui.views.DeviceItemView
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BluetoothPairingFromAScannedDeviceScreen(
    bluetoothScanningPermissions: Array<String>,
    areBluetoothScanningPermissionsGranted: () -> Boolean,
    isBluetoothEnabled: Boolean,
    isBluetoothServiceStarted: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    isDiscoveringFlow: StateFlow<Boolean>,
    startDiscovery: () -> Unit,
    cancelDiscovery: () -> Unit,
    connectToDevice: (String) -> Unit,
    disconnectDevice: () -> Unit,
    openRemoteScreen: (deviceName: String) -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    CheckMultiplePermissions(
        permissions = bluetoothScanningPermissions,
        arePermissionsGranted = areBluetoothScanningPermissionsGranted,
        onPermissionsDenied = navigateUp
    ) {
        BluetoothPairingFromAScannedDeviceScreen(
            isBluetoothEnabled = isBluetoothEnabled,
            isBluetoothServiceStarted = isBluetoothServiceStarted,
            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
            navigateUp = navigateUp,
            isDiscoveringFlow = isDiscoveringFlow,
            startDiscovery = startDiscovery,
            cancelDiscovery = cancelDiscovery,
            connectToDevice = connectToDevice,
            disconnectDevice = disconnectDevice,
            openRemoteScreen = openRemoteScreen,
            openSettings = openSettings,
            modifier = modifier
        )
    }
}

@Composable
private fun BluetoothPairingFromAScannedDeviceScreen(
    isBluetoothEnabled: Boolean,
    isBluetoothServiceStarted: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    isDiscoveringFlow: StateFlow<Boolean>,
    startDiscovery: () -> Unit,
    cancelDiscovery: () -> Unit,
    connectToDevice: (String) -> Unit,
    disconnectDevice: () -> Unit,
    openRemoteScreen: (deviceName: String) -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val devices = remember { mutableStateListOf<DeviceEntity>() }
    val isDiscovering: Boolean by isDiscoveringFlow.collectAsStateWithLifecycle()
    var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }

    DisposableEffect(isBluetoothEnabled) {
        if(!isBluetoothEnabled) {
            navigateUp()
        }
        onDispose {
            cancelDiscovery()
        }
    }

    DisposableEffect(isBluetoothServiceStarted) {
        if(!isBluetoothServiceStarted) {
            navigateUp()
        }
        onDispose {
            cancelDiscovery()
        }
    }

    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTED) {
            openRemoteScreen(bluetoothDeviceHidConnectionState.deviceName)
        }
        onDispose {
            cancelDiscovery()
        }
    }

    BackHandler(enabled = true, onBack = navigateUp)

    OnLifecycleEvent { _, event ->
        when(event) {
            Lifecycle.Event.ON_START -> startDiscovery()
            Lifecycle.Event.ON_STOP -> cancelDiscovery()
            else -> {}
        }
    }

    SystemBroadcastReceiver(systemAction = BluetoothDevice.ACTION_FOUND) { intent ->
        if(intent?.action == BluetoothDevice.ACTION_FOUND) {
            intent.parcelable(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)?.let { device: BluetoothDevice ->
                if(checkBluetoothConnectPermission(context)) {
                    if(!devices.any { it.macAddress == device.address }) {
                        devices.add(
                            DeviceEntity(
                                name = device.name ?: "null",
                                macAddress = device.address ?: "null",
                                category = device.bluetoothClass.majorDeviceClass
                            )
                        )
                    }
                }
            }
        }
    }

    StatelessBluetoothScanningScreen(
        isDiscovering = isDiscovering,
        devices = devices,
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        navigateUp = navigateUp,
        openSettings = openSettings,
        startDiscovery = startDiscovery,
        connectToDevice = connectToDevice,
        disconnectDevice = disconnectDevice,
        showHelpBottomSheet = showHelpBottomSheet,
        onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
        modifier = modifier
    )
}

@Composable
private fun StatelessBluetoothScanningScreen(
    isDiscovering: Boolean,
    devices: List<DeviceEntity>,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    openSettings: () -> Unit,
    startDiscovery: () -> Unit,
    connectToDevice: (String) -> Unit,
    disconnectDevice: () -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.pairing_a_device),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        },
        topBarActions = {
            RefreshAction(refresh = startDiscovery)
            HelpAction(showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) })
            SettingsAction(openSettings)
        }
    ) { innerPadding ->

        DiscoveredDevicesListView(
            isDiscovering = isDiscovering,
            devices = devices,
            connectToDevice = connectToDevice,
            modifier = Modifier,
            contentPadding = innerPadding
        )

        // Dialog / ModalBottomSheet
        when {
            bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTING -> {
                LoadingDialog(
                    title = stringResource(id = R.string.connection),
                    message = stringResource(
                        id = R.string.bluetooth_device_connecting_message,
                        bluetoothDeviceHidConnectionState.deviceName
                    ),
                    buttonText = stringResource(id = android.R.string.cancel),
                    onButtonClick = disconnectDevice
                )
            }
            showHelpBottomSheet -> {
                BluetoothScanningScreenHelpModalBottomSheet(
                    onDismissRequest = { onShowHelpBottomSheetChanged(false) },
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun DiscoveredDevicesListView(
    isDiscovering: Boolean,
    devices: List<DeviceEntity>,
    connectToDevice: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_max),
                        vertical = dimensionResource(id = R.dimen.padding_normal)
                    )
            ) {
                if(isDiscovering || devices.isNotEmpty()) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_icon_size))
                    ) {
                        TextNormalSecondary(
                            text = stringResource(id = R.string.available_devices),
                        )
                        if(isDiscovering) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.5.dp
                            )
                        }
                    }
                } else {
                    TextNormalSecondary(
                        text = stringResource(id = R.string.bluetooth_pairing_device_not_found),
                        modifier = Modifier
                    )
                }
            }
        }
        items(devices) { device ->
            DeviceItemView(
                name = device.name,
                macAddress = device.macAddress,
                icon = device.imageVector,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { connectToDevice(device.macAddress) }
                    .padding(dimensionResource(id = R.dimen.padding_max))
            )
        }
    }
}