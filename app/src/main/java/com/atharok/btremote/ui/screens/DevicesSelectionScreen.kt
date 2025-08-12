package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.isMacAddress
import com.atharok.btremote.domain.entities.DeviceEntity
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.BluetoothPairingFromARemoteDeviceDropdownMenuItem
import com.atharok.btremote.ui.components.BluetoothPairingFromAScannedDeviceDropdownMenuItem
import com.atharok.btremote.ui.components.BluetoothPairingOverflowMenu
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.components.EnterBluetoothAddressManuallyDropdownMenuItem
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.SimpleDialog
import com.atharok.btremote.ui.components.TemplateDialog
import com.atharok.btremote.ui.components.TextLarge
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormal
import com.atharok.btremote.ui.components.TextNormalError
import com.atharok.btremote.ui.components.TextNormalSecondary
import com.atharok.btremote.ui.views.DeviceItemView
import com.atharok.btremote.ui.views.DevicesSelectionScreenHelpModalBottomSheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private data class InternalDevice(val name: String = "", val macAddress: String = "")

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
    connectDevice: (String) -> Unit,
    disconnectDevice: () -> Unit,
    unpairDevice: (address: String) -> Boolean,
    autoConnectionDeviceAddressFlow: Flow<String>,
    saveAutoConnectionDeviceAddress: (String) -> Unit,
    favoriteDevicesFlow: Flow<List<String>>,
    saveFavoriteDevicesAddress: (List<String>) -> Unit,
    openRemoteScreen: (deviceName: String) -> Unit,
    openPairingFromAScannedDeviceScreen: () -> Unit,
    openPairingFromARemoteDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    val devices by devicesFlow.collectAsStateWithLifecycle()
    val autoConnectionDeviceAddress: String by autoConnectionDeviceAddressFlow.collectAsStateWithLifecycle("")

    val favoriteDevices by favoriteDevicesFlow.collectAsStateWithLifecycle(listOf())

    var showBluetoothAddressDialog: Boolean by remember { mutableStateOf(false) }
    var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }
    var deviceToAutoConnect: InternalDevice by remember { mutableStateOf(InternalDevice()) }
    var deviceToUnpair: InternalDevice by remember { mutableStateOf(InternalDevice()) }

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
        snackbarHostState = snackbarHostState,
        isBluetoothServiceStarted = isBluetoothServiceStarted,
        isBluetoothHidProfileRegistered = isBluetoothHidProfileRegistered,
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,

        favoriteDevices = devices.filter { it.macAddress in favoriteDevices },
        nonFavoriteDevices = devices.filter { it.macAddress !in favoriteDevices },
        connectDevice = connectDevice,
        autoConnectDeviceAddress = autoConnectionDeviceAddress,
        openPairingFromAScannedDeviceScreen = openPairingFromAScannedDeviceScreen,
        openPairingFromARemoteDeviceScreen = openPairingFromARemoteDeviceScreen,
        openSettings = openSettings,

        failureHidConnectionDialog = {
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
        },
        connectingDialog = {
            LoadingDialog(
                title = stringResource(id = R.string.connection),
                message = stringResource(id = R.string.bluetooth_device_connecting_message, bluetoothDeviceHidConnectionState.deviceName),
                buttonText = stringResource(id = android.R.string.cancel),
                onButtonClick = disconnectDevice
            )
        },
        bluetoothAddressDialog = {
            BluetoothAddressDialog(
                connectDevice = {
                    showBluetoothAddressDialog = false
                    connectDevice(it)
                },
                close = {
                    showBluetoothAddressDialog = false
                }
            )
        },
        helpBottomSheet = {
            DevicesSelectionScreenHelpModalBottomSheet(
                onDismissRequest = { showHelpBottomSheet = false }
            )
        },
        unpairDeviceDialog = {
            SimpleDialog(
                confirmButtonText = stringResource(id = R.string.unpair),
                dismissButtonText = stringResource(id = android.R.string.cancel),
                onConfirmation = {
                    val msg = if(unpairDevice(deviceToUnpair.macAddress)) {
                        context.getString(R.string.unpair_device_successful)
                    } else {
                        context.getString(R.string.unpair_device_failure)
                    }
                    coroutineScope.launch { snackbarHostState.showSnackbar(msg) }
                    deviceToUnpair = InternalDevice()
                },
                onDismissRequest = {
                    deviceToUnpair = InternalDevice()
                },
                dialogTitle = deviceToUnpair.name,
                dialogText = stringResource(id = R.string.unpair_device_warning_message)
            )
        },
        autoConnectDeviceDialog = {
            if(autoConnectionDeviceAddress == deviceToAutoConnect.macAddress) {
                // Disabled auto connect
                SimpleDialog(
                    confirmButtonText = stringResource(id = android.R.string.ok),
                    dismissButtonText = stringResource(id = android.R.string.cancel),
                    onConfirmation = {
                        saveAutoConnectionDeviceAddress("")
                        deviceToAutoConnect = InternalDevice()
                    },
                    onDismissRequest = {
                        deviceToAutoConnect = InternalDevice()
                    },
                    dialogTitle = deviceToAutoConnect.name,
                    dialogText = stringResource(id = R.string.disabled_automatic_connection_message)
                )
            } else {
                // Enabled auto connect
                SimpleDialog(
                    confirmButtonText = stringResource(id = android.R.string.ok),
                    dismissButtonText = stringResource(id = android.R.string.cancel),
                    onConfirmation = {
                        saveAutoConnectionDeviceAddress(deviceToAutoConnect.macAddress)
                        deviceToAutoConnect = InternalDevice()
                    },
                    onDismissRequest = {
                        deviceToAutoConnect = InternalDevice()
                    },
                    dialogTitle = deviceToAutoConnect.name,
                    dialogText = stringResource(id = R.string.enabled_automatic_connection_message)
                )
            }
        },

        showBluetoothAddressDialog = showBluetoothAddressDialog,
        onShowBluetoothAddressDialogChanged = { showBluetoothAddressDialog = it },
        showHelpBottomSheet = showHelpBottomSheet,
        onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
        deviceToUnpair = deviceToUnpair,
        onDeviceToUnpairChanged = { deviceToUnpair = it },
        deviceToAutoConnect = deviceToAutoConnect,
        onDeviceToAutoConnectChanged = { deviceToAutoConnect = it },
        saveFavoriteDevice = {
            if(favoriteDevices.contains(it)) {
                saveFavoriteDevicesAddress(favoriteDevices - it)
            } else {
                saveFavoriteDevicesAddress(favoriteDevices + it)
            }
        },

        modifier = modifier
    )
}

@Composable
private fun StatelessDevicesSelectionScreen(
    snackbarHostState: SnackbarHostState,
    isBluetoothServiceStarted: Boolean,
    isBluetoothHidProfileRegistered: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,

    favoriteDevices: List<DeviceEntity>,
    nonFavoriteDevices: List<DeviceEntity>,
    connectDevice: (String) -> Unit,
    autoConnectDeviceAddress: String,
    openPairingFromAScannedDeviceScreen: () -> Unit,
    openPairingFromARemoteDeviceScreen: () -> Unit,
    openSettings: () -> Unit,

    failureHidConnectionDialog: @Composable () -> Unit,
    connectingDialog: @Composable () -> Unit,
    helpBottomSheet: @Composable () -> Unit,
    bluetoothAddressDialog: @Composable () -> Unit,
    unpairDeviceDialog: @Composable () -> Unit,
    autoConnectDeviceDialog: @Composable () -> Unit,

    showBluetoothAddressDialog: Boolean,
    onShowBluetoothAddressDialogChanged: (Boolean) -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    deviceToUnpair: InternalDevice,
    onDeviceToUnpairChanged: (InternalDevice) -> Unit,
    deviceToAutoConnect: InternalDevice,
    onDeviceToAutoConnectChanged: (InternalDevice) -> Unit,
    saveFavoriteDevice: (String) -> Unit,

    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.devices),
        modifier = modifier,
        topBarActions = {
            BluetoothPairingOverflowMenu { closeDropdownMenu: () -> Unit ->
                BluetoothPairingFromAScannedDeviceDropdownMenuItem(
                    openBluetoothPairingScreen = {
                        openPairingFromAScannedDeviceScreen()
                        closeDropdownMenu()
                    }
                )
                BluetoothPairingFromARemoteDeviceDropdownMenuItem(
                    openBluetoothPairingScreen = {
                        openPairingFromARemoteDeviceScreen()
                        closeDropdownMenu()
                    }
                )
                EnterBluetoothAddressManuallyDropdownMenuItem(
                    onClick = {
                        onShowBluetoothAddressDialogChanged(true)
                        closeDropdownMenu()
                    }
                )
            }

            HelpAction(showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) })
            SettingsAction(openSettings)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        DevicesListView(
            favoriteDevices = favoriteDevices,
            nonFavoriteDevices = nonFavoriteDevices,
            connectDevice = connectDevice,
            autoConnectDeviceAddress = autoConnectDeviceAddress,
            autoConnect = onDeviceToAutoConnectChanged,
            saveFavoriteDevice = saveFavoriteDevice,
            unpairDevice = onDeviceToUnpairChanged,
            isBluetoothServiceStarted = isBluetoothServiceStarted,
            modifier = Modifier,
            contentPadding = innerPadding
        )

        // Dialog / ModalBottomSheet
        when {
            isBluetoothServiceStarted && !isBluetoothHidProfileRegistered -> failureHidConnectionDialog()
            bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTING -> connectingDialog()
            showHelpBottomSheet -> helpBottomSheet()
            showBluetoothAddressDialog -> bluetoothAddressDialog()
            deviceToUnpair.macAddress != "" -> unpairDeviceDialog()
            deviceToAutoConnect.macAddress != "" -> autoConnectDeviceDialog()
        }
    }
}

@Composable
private fun DevicesListView(
    favoriteDevices: List<DeviceEntity>,
    nonFavoriteDevices: List<DeviceEntity>,
    connectDevice: (String) -> Unit,
    autoConnectDeviceAddress: String,
    autoConnect: (InternalDevice) -> Unit,
    saveFavoriteDevice: (String) -> Unit,
    unpairDevice: (InternalDevice) -> Unit,
    isBluetoothServiceStarted: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            InfoView(
                isBluetoothServiceStarted = isBluetoothServiceStarted,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_max),
                    vertical = dimensionResource(R.dimen.padding_large)
                )
            )
        }

        // Favorite devices

        if(favoriteDevices.isNotEmpty()) {
            item {
                TextNormalSecondary(
                    text = stringResource(id = R.string.favorites),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_max),
                            vertical = dimensionResource(id = R.dimen.padding_normal)
                        )
                )
            }
            items(favoriteDevices) { device ->
                DeviceItemView(
                    name = device.name,
                    macAddress = device.macAddress,
                    icon = device.imageVector,
                    isAutoConnectDeviceAddress = autoConnectDeviceAddress == device.macAddress,
                    autoConnect = { autoConnect(InternalDevice(device.name, device.macAddress)) },
                    isFavoriteDevice = true,
                    onFavoriteDeviceChanged = {
                        saveFavoriteDevice(device.macAddress)
                    },
                    unpair = { unpairDevice(InternalDevice(device.name, device.macAddress)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { connectDevice(device.macAddress) }
                        .padding(dimensionResource(id = R.dimen.padding_max))
                )
            }
        }

        // Non favorite devices

        if(nonFavoriteDevices.isNotEmpty()) {
            item {
                TextNormalSecondary(
                    text = stringResource(id = R.string.paired_devices),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_max),
                            vertical = dimensionResource(id = R.dimen.padding_normal)
                        )
                )
            }
            items(nonFavoriteDevices) { device ->
                DeviceItemView(
                    name = device.name,
                    macAddress = device.macAddress,
                    icon = device.imageVector,
                    isAutoConnectDeviceAddress = autoConnectDeviceAddress == device.macAddress,
                    autoConnect = { autoConnect(InternalDevice(device.name, device.macAddress)) },
                    isFavoriteDevice = false,
                    onFavoriteDeviceChanged = {
                        saveFavoriteDevice(device.macAddress)
                    },
                    unpair = { unpairDevice(InternalDevice(device.name, device.macAddress)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { connectDevice(device.macAddress) }
                        .padding(dimensionResource(id = R.dimen.padding_max))
                )
            }
        }
    }
}

@Composable
private fun InfoView(
    isBluetoothServiceStarted: Boolean,
    modifier: Modifier = Modifier
) {
    DefaultElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_max)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
            ) {
                Icon(
                    imageVector = AppIcons.Info,
                    contentDescription = stringResource(R.string.information)
                )
                TextMedium(text = stringResource(R.string.information))
            }
            if(!isBluetoothServiceStarted) {
                TextNormalError(
                    text = stringResource(R.string.help_info_hid_service_not_initialized_message)
                )
            } else {
                TextNormalSecondary(text = stringResource(R.string.help_info_message))
            }
        }
    }
}

@Composable
private fun BluetoothAddressDialog(
    connectDevice: (String) -> Unit,
    close: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val textState = remember { mutableStateOf("") }
    val showError = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TemplateDialog(
        title = {
            TextLarge(text = stringResource(id = R.string.bluetooth_address))
        },
        content = {
            Column {
                TextNormal(text = stringResource(id = R.string.bluetooth_address_prompt))
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if(isMacAddress(textState.value)) {
                                connectDevice(textState.value)
                            } else {
                                showError.value = true
                            }
                        }
                    ),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                if(showError.value) {
                    TextNormalError(text = stringResource(id = R.string.bluetooth_address_wrong_format))
                }
            }
        },
        modifier = modifier,
        confirmButtonText = stringResource(id = R.string.connection),
        onConfirmation = {
            if(isMacAddress(textState.value)) {
                connectDevice(textState.value)
            } else {
                showError.value = true
            }
        },
        dismissButtonText = stringResource(id = R.string.close),
        onDismissRequest = close
    )
}