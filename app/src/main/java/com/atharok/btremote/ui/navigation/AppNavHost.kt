package com.atharok.btremote.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

private val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth }
    )
}

private val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally (
        targetOffsetX = { fullWidth -> -fullWidth }
    )
}

private val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth }
    )
}

private val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally (
        targetOffsetX = { fullWidth -> fullWidth }
    )
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    bluetoothPermissionsScreen: @Composable () -> Unit,
    settingsScreen: @Composable () -> Unit,
    thirdLibrariesScreen: @Composable () -> Unit,
    bluetoothActivationScreen: @Composable () -> Unit,
    bluetoothDeviceSelectionScreen: @Composable () -> Unit,
    bluetoothPairingFromAScannedDeviceScreen: @Composable () -> Unit,
    bluetoothPairingFromARemoteDeviceScreen: @Composable () -> Unit,
    bluetoothRemoteScreen: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppNavDestination.BluetoothPermissionsDestination.route,
        modifier = modifier
    ) {

        composable(
            route = AppNavDestination.BluetoothPermissionsDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothPermissionsScreen()
        }

        composable(
            route = AppNavDestination.SettingsDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            settingsScreen()
        }

        composable(
            route = AppNavDestination.ThirdLibrariesDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            thirdLibrariesScreen()
        }

        composable(
            route = AppNavDestination.BluetoothActivationDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothActivationScreen()
        }

        composable(
            route = AppNavDestination.BluetoothDeviceSelectionDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothDeviceSelectionScreen()
        }

        composable(
            route = AppNavDestination.BluetoothPairingFromAScannedDeviceDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothPairingFromAScannedDeviceScreen()
        }

        composable(
            route = AppNavDestination.BluetoothPairingFromARemoteDeviceDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothPairingFromARemoteDeviceScreen()
        }

        composable(
            route = AppNavDestination.BluetoothRemoteDestination.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            bluetoothRemoteScreen()
        }
    }
}

fun NavHostController.navigateTo(
    route: String,
    launchSingleTop: Boolean = true
) {
    this.navigate(route) {
        this.launchSingleTop = launchSingleTop
    }
}