package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.extensions.getActivity
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.ui.views.ActivationView

@Composable
fun BluetoothActivationScreen(
    isBluetoothEnabled: Boolean,
    openBluetoothDeviceSelectionScreen: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulBluetoothActivationScreen(
        isBluetoothEnabled = isBluetoothEnabled,
        openBluetoothDeviceSelectionScreen =openBluetoothDeviceSelectionScreen
    ) { enableBluetooth: () -> Unit ->
        ActivationView(
            topBarTitle = stringResource(id = R.string.activation),
            image = AppIcons.BluetoothDisabled,
            title = stringResource(id = R.string.bluetooth_disabled_info),
            message = stringResource(id = R.string.bluetooth_disabled_message),
            buttonIcon = AppIcons.Bluetooth,
            buttonText = stringResource(id = R.string.bluetooth_enabled_button),
            buttonOnClick = enableBluetooth,
            openSettings = openSettings,
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulBluetoothActivationScreen(
    isBluetoothEnabled: Boolean,
    openBluetoothDeviceSelectionScreen: () -> Unit,
    content: @Composable (enableBluetooth: () -> Unit) -> Unit
) {
    val context = LocalContext.current

    DisposableEffect(isBluetoothEnabled) {
        if(isBluetoothEnabled) {
            openBluetoothDeviceSelectionScreen()
        }
        onDispose {}
    }

    content {
        if (checkBluetoothConnectPermission(context)) {
            (context.getActivity())?.let { activity ->
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                activity.startActivity(enableBtIntent)
            }
        }
    }
}