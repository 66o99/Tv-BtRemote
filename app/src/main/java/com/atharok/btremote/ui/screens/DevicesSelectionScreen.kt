package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.PairingNewDeviceAction
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.SimpleDialog
import com.atharok.btremote.ui.components.TextNormalSecondary
import com.atharok.btremote.ui.views.DeviceItemView
import com.atharok.btremote.ui.views.DevicesSelectionScreenHelpModalBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun DevicesSelectionScreen(
    isBluetoothEnabled: Boolean,
    isBluetoothServiceStarted: Boolean,
    isBluetoothHidProfileRegistered: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    closeApp: () -> Unit,
    navigateUp: () -> Unit,
    startHidService: () -> Unit,
    stopHidService: () -> Unit,
    devicesFlow: StateFlow<List<DeviceEntity>>,
    findBondedDevices: () -> Unit,
    connectDevice: (DeviceEntity) -> Unit,
    disconnectDevice: () -> Unit,
    unpairDevice: (address: String) -> Boolean,
    openRemoteScreen: (deviceName: String) -> Unit,
    openBluetoothScanningDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val devices by devicesFlow.collectAsStateWithLifecycle()
    var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }
    var deviceAddressToUnpair: String by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    DisposableEffect(isBluetoothEnabled) {
        if(!isBluetoothEnabled) {
            stopHidService()
            navigateUp()
        }
        onDispose {}
    }

    DisposableEffect(isBluetoothServiceStarted) {
        if(!isBluetoothServiceStarted && isBluetoothEnabled) {
            startHidService()
        }
        onDispose {}
    }

    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTED) {
            openRemoteScreen(bluetoothDeviceHidConnectionState.deviceName)
        }
        onDispose {}
    }

    BackHandler(enabled = true, onBack = closeApp)

    LaunchedEffect(Unit) {
        findBondedDevices()
    }

    StatelessDevicesSelectionScreen(
        context = context,
        coroutineScope = coroutineScope,
        snackbarHostState = snackbarHostState,
        isBluetoothServiceStarted = isBluetoothServiceStarted,
        isBluetoothHidProfileRegistered = isBluetoothHidProfileRegistered,
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        closeApp = closeApp,
        startHidService = startHidService,
        stopHidService = stopHidService,
        devices = devices,
        connectDevice = connectDevice,
        disconnectDevice = disconnectDevice,
        unpairDevice = unpairDevice,
        openBluetoothScanningDeviceScreen = openBluetoothScanningDeviceScreen,
        openSettings = openSettings,
        showHelpBottomSheet = showHelpBottomSheet,
        onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
        deviceAddressToUnpair = deviceAddressToUnpair,
        onDeviceAddressToUnpairChanged = { deviceAddressToUnpair = it },
        modifier = modifier
    )
}

@Composable
private fun StatelessDevicesSelectionScreen(
    context: Context,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    isBluetoothServiceStarted: Boolean,
    isBluetoothHidProfileRegistered: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    closeApp: () -> Unit,
    startHidService: () -> Unit,
    stopHidService: () -> Unit,
    devices: List<DeviceEntity>,
    connectDevice: (DeviceEntity) -> Unit,
    disconnectDevice: () -> Unit,
    unpairDevice: (String) -> Boolean,
    openBluetoothScanningDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    deviceAddressToUnpair: String,
    onDeviceAddressToUnpairChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.devices),
        modifier = modifier,
        topBarActions = {
            PairingNewDeviceAction(openBluetoothScanningDeviceScreen)
            HelpAction(showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) })
            SettingsAction(openSettings)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->

        DevicesListView(
            devices = devices,
            onItemClick = connectDevice,
            unpairDevice = onDeviceAddressToUnpairChanged,
            modifier = Modifier,
            contentPadding = innerPadding
        )

        // Dialog / ModalBottomSheet
        when {
            isBluetoothServiceStarted && !isBluetoothHidProfileRegistered -> {
                SimpleDialog(
                    confirmButtonText = stringResource(id = R.string.retry),
                    dismissButtonText = stringResource(id = R.string.close),
                    onConfirmation = {
                        stopHidService()
                        startHidService()
                    },
                    onDismissRequest = closeApp,
                    dialogTitle = stringResource(id = R.string.error),
                    dialogText = stringResource(id = R.string.bluetooth_failed_to_register_app_message)
                )
            }
            bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTING -> {
                LoadingDialog(
                    title = stringResource(id = R.string.connection),
                    message = stringResource(id = R.string.bluetooth_device_connecting_message, bluetoothDeviceHidConnectionState.deviceName),
                    buttonText = stringResource(id = android.R.string.cancel),
                    onButtonClick = disconnectDevice
                )
            }
            showHelpBottomSheet -> {
                DevicesSelectionScreenHelpModalBottomSheet(
                    onDismissRequest = { onShowHelpBottomSheetChanged(false) },
                    modifier = modifier
                )
            }
            deviceAddressToUnpair != "" -> {
                SimpleDialog(
                    confirmButtonText = stringResource(id = R.string.unpair),
                    dismissButtonText = stringResource(id = android.R.string.cancel),
                    onConfirmation = {
                        val msg = if(unpairDevice(deviceAddressToUnpair)) {
                            context.getString(R.string.unpair_device_successful)
                        } else {
                            context.getString(R.string.unpair_device_failure)
                        }
                        coroutineScope.launch { snackbarHostState.showSnackbar(msg) }
                        onDeviceAddressToUnpairChanged("")
                    },
                    onDismissRequest = {
                        onDeviceAddressToUnpairChanged("")
                    },
                    dialogTitle = stringResource(id = R.string.unpair_device),
                    dialogText = stringResource(id = R.string.unpair_device_warning_message)
                )
            }
        }
    }
}

@Composable
private fun DevicesListView(
    devices: List<DeviceEntity>,
    onItemClick: (DeviceEntity) -> Unit,
    unpairDevice: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            InfoView(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_large),
                    vertical = dimensionResource(R.dimen.padding_medium)
                )
            )
        }
        item {
            TextNormalSecondary(
                text = stringResource(id = R.string.paired_devices),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_large),
                        vertical = dimensionResource(id = R.dimen.remote_button_padding)
                    )
            )
        }
        items(devices) { device ->
            DeviceItemView(
                name = device.name,
                macAddress = device.macAddress,
                icon = device.imageVector,
                unpair = { unpairDevice(device.macAddress) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(device) }
                    .padding(dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}

@Composable
private fun InfoView(
    modifier: Modifier = Modifier
) {
    DefaultElevatedCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Icon(
                imageVector = AppIcons.Info,
                contentDescription = stringResource(R.string.information)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TextNormalSecondary(text = stringResource(R.string.help_info_message))
            }
        }
    }
}