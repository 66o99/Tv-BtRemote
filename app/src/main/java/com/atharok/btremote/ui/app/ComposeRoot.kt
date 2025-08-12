package com.atharok.btremote.ui.app

import android.content.Context
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.atharok.btremote.common.extensions.getActivity
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.CheckBluetoothActivation
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.navigation.AppNavDestination
import com.atharok.btremote.ui.navigation.AppNavHost
import com.atharok.btremote.ui.navigation.navigateTo
import com.atharok.btremote.ui.screens.BluetoothActivationScreen
import com.atharok.btremote.ui.screens.BluetoothNotSupportScreen
import com.atharok.btremote.ui.screens.BluetoothPairingFromARemoteDeviceScreen
import com.atharok.btremote.ui.screens.BluetoothPairingFromAScannedDeviceScreen
import com.atharok.btremote.ui.screens.BluetoothPermissionsScreen
import com.atharok.btremote.ui.screens.DevicesSelectionScreen
import com.atharok.btremote.ui.screens.RemoteScreen
import com.atharok.btremote.ui.screens.SettingsScreen
import com.atharok.btremote.ui.screens.ThirdLibrariesScreen
import com.atharok.btremote.ui.theme.BtRemoteTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeRoot(
    navController: NavHostController = rememberNavController(),
    settingsViewModel: SettingsViewModel = koinViewModel(),
    bluetoothViewModel: BluetoothViewModel = koinViewModel(),
    hidViewModel: BluetoothHidViewModel = koinViewModel(),
    openSettings: () -> Unit = {
        navController.navigateTo(AppNavDestination.SettingsDestination.route)
    }
) {
    BtRemoteTheme(settingsViewModel) {
        Surface(modifier = Modifier.fillMaxSize()) {
            if(!bluetoothViewModel.isBluetoothSupported) {
                BluetoothNotSupportScreen()
            } else {

                val context: Context = LocalContext.current

                var isBluetoothEnabled: Boolean by remember { mutableStateOf(bluetoothViewModel.isBluetoothEnable()) }

                CheckBluetoothActivation(
                    isBluetoothEnabled = isBluetoothEnabled,
                    onBluetoothEnabledChanged = { isBluetoothEnabled = it }
                )

                val isBluetoothServiceStarted by hidViewModel.isBluetoothServiceStarted().collectAsStateWithLifecycle()
                val isBluetoothHidProfileRegistered by hidViewModel.isBluetoothHidProfileRegistered().collectAsStateWithLifecycle()
                val bluetoothDeviceHidConnectionState by hidViewModel.getDeviceHidConnectionState().collectAsStateWithLifecycle()

                // ---- NavHost ----

                AppNavHost(
                    navController = navController,

                    bluetoothPermissionsScreen = {
                        BluetoothPermissionsScreen(
                            permissions = bluetoothViewModel.getBluetoothPermissions(),
                            arePermissionsGranted = { bluetoothViewModel.areBluetoothPermissionsGranted() },
                            onPermissionsGranted = {
                                navController.navigate(AppNavDestination.BluetoothActivationDestination.route) {
                                    popUpTo(0) {
                                        this.saveState = false
                                    }
                                }
                            },
                            openSettings = openSettings,
                            modifier = Modifier
                        )
                    },

                    settingsScreen = {
                        SettingsScreen(
                            navigateUp = { navController.navigateUp() },
                            openThirdLibrariesScreen = {
                                navController.navigateTo(AppNavDestination.ThirdLibrariesDestination.route)
                            },
                            settingsViewModel = settingsViewModel,
                            modifier = Modifier
                        )
                    },

                    thirdLibrariesScreen = {
                        ThirdLibrariesScreen(
                            navigateUp = { navController.navigateUp() },
                            modifier = Modifier
                        )
                    },

                    bluetoothActivationScreen = {
                        BluetoothActivationScreen(
                            isBluetoothEnabled = isBluetoothEnabled,
                            openBluetoothDeviceSelectionScreen = {
                                navController.navigateTo(AppNavDestination.BluetoothDeviceSelectionDestination.route)
                            },
                            openSettings = openSettings
                        )
                    },

                    bluetoothDeviceSelectionScreen = {
                        DevicesSelectionScreen(
                            isBluetoothEnabled = isBluetoothEnabled,
                            isBluetoothServiceStarted = isBluetoothServiceStarted,
                            isBluetoothHidProfileRegistered = isBluetoothHidProfileRegistered,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            closeApp = { context.getActivity()?.moveTaskToBack(true) },
                            navigateUp = { navController.navigateUp() },
                            startHidService = { hidViewModel.startService(context) },
                            stopHidService = { hidViewModel.stopService(context) },
                            devicesFlow = bluetoothViewModel.devicesEntityObserver,
                            findBondedDevices = { bluetoothViewModel.findBondedDevices() },
                            connectDevice = { macAddress ->
                                if(!hidViewModel.connectDevice(macAddress)) {
                                    hidViewModel.disconnectDevice()
                                    hidViewModel.connectDevice(macAddress)
                                }
                            },
                            disconnectDevice = { hidViewModel.disconnectDevice() },
                            unpairDevice = { bluetoothViewModel.unpairDevice(it) },
                            autoConnectionDeviceAddressFlow= hidViewModel.getAutoConnectDeviceAddressFlow(),
                            saveAutoConnectionDeviceAddress = {
                                hidViewModel.saveAutoConnectDeviceAddress(it)
                            },
                            favoriteDevicesFlow = settingsViewModel.favoriteDevices,
                            saveFavoriteDevicesAddress = {
                                settingsViewModel.saveFavoriteDevices(it)
                            },
                            openRemoteScreen = {
                                navController.navigateTo(AppNavDestination.BluetoothRemoteDestination.route)
                            },
                            openPairingFromAScannedDeviceScreen = {
                                navController.navigateTo(AppNavDestination.BluetoothPairingFromAScannedDeviceDestination.route)
                            },
                            openPairingFromARemoteDeviceScreen = {
                                navController.navigateTo(AppNavDestination.BluetoothPairingFromARemoteDeviceDestination.route)
                            },
                            openSettings = openSettings,
                            modifier = Modifier
                        )
                    },

                    bluetoothPairingFromAScannedDeviceScreen = {
                        BluetoothPairingFromAScannedDeviceScreen(
                            bluetoothScanningPermissions = bluetoothViewModel.getBluetoothScanningPermissions(),
                            areBluetoothScanningPermissionsGranted = {
                                bluetoothViewModel.areBluetoothScanningPermissionsGranted()
                            },
                            isBluetoothEnabled = isBluetoothEnabled,
                            isBluetoothServiceStarted = isBluetoothServiceStarted,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            navigateUp = {
                                bluetoothViewModel.cancelDiscovery()
                                navController.navigateUp()
                            },
                            isDiscoveringFlow = bluetoothViewModel.isDiscovering,
                            startDiscovery = { bluetoothViewModel.startDiscovery() },
                            cancelDiscovery = { bluetoothViewModel.cancelDiscovery() },
                            connectToDevice = { macAddress ->
                                bluetoothViewModel.cancelDiscovery()
                                hidViewModel.connectDevice(macAddress)
                            },
                            disconnectDevice = { hidViewModel.disconnectDevice() },
                            openRemoteScreen = {
                                navController.navigateTo(AppNavDestination.BluetoothRemoteDestination.route)
                            },
                            openSettings = openSettings,
                            modifier = Modifier
                        )
                    },

                    bluetoothPairingFromARemoteDeviceScreen = {
                        BluetoothPairingFromARemoteDeviceScreen(
                            isBluetoothEnabled = isBluetoothEnabled,
                            localDeviceName = bluetoothViewModel.getLocalDeviceName(),
                            navigateUp = {
                                navController.navigateUp()
                            },
                            modifier = Modifier
                        )
                    },

                    bluetoothRemoteScreen = {
                        RemoteScreen(
                            deviceName = bluetoothDeviceHidConnectionState.deviceName,
                            isBluetoothServiceStarted = isBluetoothServiceStarted,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            navigateUp = { navController.navigateUp() },
                            closeApp = { context.getActivity()?.moveTaskToBack(true) },
                            openSettings = openSettings,
                            settingsViewModel = settingsViewModel,
                            disconnectDevice = {
                                hidViewModel.disconnectDevice()
                            },
                            forceDisconnectDevice = {
                                hidViewModel.disconnectDevice()
                                hidViewModel.stopService(context)
                            },
                            sendRemoteKeyReport = { bytes -> hidViewModel.sendRemoteKeyReport(bytes) },
                            sendMouseKeyReport = { input: MouseAction, x: Float, y: Float, wheel: Float -> hidViewModel.sendMouseKeyReport(input, x, y, wheel) },
                            sendKeyboardKeyReport = { bytes -> hidViewModel.sendKeyboardKeyReport(bytes) },
                            sendTextReport = { text, keyboardLayout -> hidViewModel.sendTextReport(text, keyboardLayout) },
                            modifier = Modifier
                        )
                    },
                    modifier = Modifier.windowInsetsPadding(WindowInsets.displayCutout.exclude(WindowInsets.systemBars))
                )

                // ---- Lifecycle ----

                OnLifecycleEvent { _, event ->
                    when(event) {
                        Lifecycle.Event.ON_RESUME -> {
                            // Si une permission a été retirée par l'utilisateur pendant l'utilisation de l'application, on affiche l'écran de permissions
                            if(!bluetoothViewModel.areBluetoothPermissionsGranted() && navController.currentDestination?.route != AppNavDestination.BluetoothPermissionsDestination.route) {
                                navController.navigate(AppNavDestination.BluetoothPermissionsDestination.route) {
                                    popUpTo(0) {
                                        this.saveState = false
                                    }
                                }
                            }
                        }
                        Lifecycle.Event.ON_STOP -> {
                            if(navController.currentDestination?.route == AppNavDestination.BluetoothDeviceSelectionDestination.route) {
                                if(isBluetoothServiceStarted) {
                                    hidViewModel.stopService(context)
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}