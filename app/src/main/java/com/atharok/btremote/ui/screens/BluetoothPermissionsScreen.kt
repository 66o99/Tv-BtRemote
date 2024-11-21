package com.atharok.btremote.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.RequestMultiplePermissions
import com.atharok.btremote.ui.views.ActivationView

@Composable
fun BluetoothPermissionsScreen(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    onPermissionsGranted: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulBluetoothPermission(
        permissions = permissions,
        arePermissionsGranted = arePermissionsGranted,
        onPermissionsGranted = onPermissionsGranted
    ) { requestPermission: () -> Unit ->
        StatelessBluetoothPermission(
            requestPermission = requestPermission,
            openSettings = openSettings,
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulBluetoothPermission(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    onPermissionsGranted: () -> Unit,
    content: @Composable (() -> Unit) -> Unit
) {
    if(arePermissionsGranted()) {
        DisposableEffect(Unit) {
            onPermissionsGranted()
            onDispose {}
        }
    } else {
        var permissionRequested: Boolean by remember { mutableStateOf(false) }

        if (permissionRequested) {
            RequestMultiplePermissions(
                permissions = permissions,
                onPermissionsGranted = onPermissionsGranted,
                onPermissionsDenied = { permissionRequested = false }
            )
        }

        content {
            permissionRequested = true
        }
    }
}

@Composable
private fun StatelessBluetoothPermission(
    requestPermission: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    ActivationView(
        topBarTitle = stringResource(id = R.string.permission),
        image = AppIcons.Lock,
        title = stringResource(id = R.string.bluetooth_permission_not_granted),
        message = stringResource(id = R.string.bluetooth_permission_message),
        buttonIcon = AppIcons.Key,
        buttonText = stringResource(id = R.string.bluetooth_permission_button),
        buttonOnClick = requestPermission,
        openSettings = openSettings,
        modifier = modifier
    )
}