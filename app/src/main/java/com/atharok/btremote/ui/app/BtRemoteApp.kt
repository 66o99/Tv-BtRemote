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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.atharok.btremote.common.extensions.getActivity
import com.atharok.btremote.common.utils.arePermissionsGranted
import com.atharok.btremote.common.utils.bluetoothConnectPermissions
import com.atharok.btremote.common.utils.bluetoothScanPermissions
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.presentation.services.BluetoothHidService
import com.atharok.btremote.presentation.viewmodel.AppScopeViewModel
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.navigation.AppNavDestination
import com.atharok.btremote.ui.navigation.AppNavHost
import com.atharok.btremote.ui.navigation.navigateTo
import com.atharok.btremote.ui.screens.BluetoothActivationScreen
import com.atharok.btremote.ui.screens.BluetoothNotSupportScreen
import com.atharok.btremote.ui.screens.BluetoothPermissionsScreen
import com.atharok.btremote.ui.screens.DeviceDiscoveryScreen
import com.atharok.btremote.ui.screens.DevicesSelectionScreen
import com.atharok.btremote.ui.screens.DistantDevicePairScreen
import com.atharok.btremote.ui.screens.RemoteScreen
import com.atharok.btremote.ui.screens.SettingsScreen
import com.atharok.btremote.ui.screens.ThirdLibrariesScreen
import com.atharok.btremote.ui.theme.BtRemoteTheme
import com.atharok.btremote.ui.theme.dimensionElevation1
import org.koin.androidx.compose.koinViewModel

@Composable
fun BtRemoteApp(
    navController: NavHostController = rememberNavController(),
    appScopeViewModel: AppScopeViewModel = koinViewModel(),
    navigateToSettings: () -> Unit = {
        navController.navigateTo(AppNavDestination.SettingsDestination.route)
    },
    context: Context = LocalContext.current
) {
    BtRemoteTheme(
        appearanceFlow = appScopeViewModel.appearanceSettingsFlow
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            tonalElevation = dimensionElevation1()
        ) {
            if(!appScopeViewModel.isBluetoothSupported) {
                BluetoothNotSupportScreen()
            } else {

                val isBluetoothEnabled: Boolean by appScopeViewModel.isBluetoothEnabled.collectAsStateWithLifecycle()
                val isBluetoothServiceRunning: Boolean by appScopeViewModel.isBluetoothServiceRunning.collectAsStateWithLifecycle()
                val isBluetoothHidProfileRegistered: Boolean by appScopeViewModel.isBluetoothHidProfileRegistered.collectAsStateWithLifecycle()
                val bluetoothDeviceHidConnectionState: DeviceHidConnectionState by appScopeViewModel.deviceHidConnectionState.collectAsStateWithLifecycle()

                // BroadcastReceiver
                DisposableEffect(Unit) {
                    appScopeViewModel.registerBluetoothStateReceiver()
                    onDispose {
                        appScopeViewModel.unregisterBluetoothStateReceiver()
                    }
                }

                // ---- NavHost ----

                AppNavHost(
                    navController = navController,

                    startDestination = if(arePermissionsGranted(context, bluetoothConnectPermissions))
                        AppNavDestination.BluetoothActivationDestination.route
                    else
                        AppNavDestination.BluetoothPermissionsDestination.route,

                    bluetoothPermissionsScreen = {
                        BluetoothPermissionsScreen(
                            permissions = bluetoothConnectPermissions,
                            arePermissionsGranted = arePermissionsGranted(
                                context = context,
                                permissions = bluetoothConnectPermissions
                            ),
                            onPermissionsGranted = {
                                navController.navigate(AppNavDestination.BluetoothActivationDestination.route) {
                                    popUpTo(0) {
                                        this.saveState = false
                                    }
                                }
                            },
                            navigateToSettings = navigateToSettings,
                            modifier = Modifier
                        )
                    },

                    settingsScreen = {
                        SettingsScreen(
                            navigateUp = { navController.navigateUp() },
                            navigateToThirdLibrariesScreen = {
                                navController.navigateTo(AppNavDestination.ThirdLibrariesDestination.route)
                            },
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
                            navigateToBluetoothDeviceSelectionScreen = {
                                navController.navigateTo(AppNavDestination.DeviceSelectionDestination.route)
                            },
                            navigateToSettings = navigateToSettings
                        )
                    },

                    deviceSelectionScreen = {
                        DevicesSelectionScreen(
                            isBluetoothEnabled = isBluetoothEnabled,
                            isBluetoothServiceRunning = isBluetoothServiceRunning,
                            isBluetoothHidProfileRegistered = isBluetoothHidProfileRegistered,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            closeApp = { context.getActivity()?.moveTaskToBack(true) },
                            navigateUp = { navController.navigateUp() },
                            navigateToRemoteScreen = {
                                navController.navigateTo(AppNavDestination.RemoteDestination.route)
                            },
                            navigateToDeviceDiscoveryScreen = {
                                navController.navigateTo(AppNavDestination.DeviceDiscoveryDestination.route)
                            },
                            navigateToDistantDevicePairScreen = {
                                navController.navigateTo(AppNavDestination.DistantDevicePairDestination.route)
                            },
                            navigateToSettings = navigateToSettings,
                            modifier = Modifier
                        )
                    },

                    deviceDiscoveryScreen = {
                        DeviceDiscoveryScreen(
                            permissions = bluetoothScanPermissions,
                            arePermissionsGranted = arePermissionsGranted(
                                context = context,
                                permissions = bluetoothScanPermissions
                            ),
                            isBluetoothEnabled = isBluetoothEnabled,
                            isBluetoothServiceRunning = isBluetoothServiceRunning,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            navigateUp = { navController.navigateUp() },
                            navigateToRemoteScreen = {
                                navController.navigateTo(AppNavDestination.RemoteDestination.route)
                            },
                            navigateToSettings = navigateToSettings,
                            modifier = Modifier
                        )
                    },

                    distantDevicePairScreen = {
                        DistantDevicePairScreen(
                            isBluetoothEnabled = isBluetoothEnabled,
                            localDeviceName = appScopeViewModel.localDeviceName,
                            navigateUp = {
                                navController.navigateUp()
                            },
                            modifier = Modifier
                        )
                    },

                    remoteScreen = {
                        RemoteScreen(
                            isBluetoothServiceRunning = isBluetoothServiceRunning,
                            bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                            closeApp = { context.getActivity()?.moveTaskToBack(true) },
                            navigateUp = { navController.navigateUp() },
                            navigateToSettings = navigateToSettings,
                            modifier = Modifier
                        )
                    },
                    modifier = Modifier.windowInsetsPadding(WindowInsets.displayCutout.exclude(WindowInsets.systemBars))
                )

                // ---- Lifecycle ----

                OnLifecycleEvent { _, event ->
                    // If the app goes into the background
                    if(event == Lifecycle.Event.ON_STOP) {
                        // If we are in the device selection view
                        if(navController.currentDestination?.route == AppNavDestination.DeviceSelectionDestination.route) {
                            if(isBluetoothServiceRunning) {
                                BluetoothHidService.stop(context)
                            }
                        }
                    }
                }
            }
        }
    }
}