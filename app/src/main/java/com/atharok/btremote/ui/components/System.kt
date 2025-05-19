package com.atharok.btremote.ui.components

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }

        context.registerReceiver(broadcast, intentFilter)

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun CheckSinglePermission(
    permission: String,
    isPermissionGranted: () -> Boolean,
    doAfterGrantPermission: () -> Unit,
    permissionScreen: @Composable (grantPermissions: () -> Unit) -> Unit
) {
    var permissionIsGranted by remember { mutableStateOf(isPermissionGranted()) }

    if(permissionIsGranted) {
        doAfterGrantPermission()
    } else {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {
                permissionIsGranted = it
            }
        )

        permissionScreen {
            launcher.launch(permission)
        }
    }
}

@Composable
fun CheckSinglePermission(
    permission: String,
    isPermissionGranted: () -> Boolean,
    onPermissionDenied: () -> Unit,
    content: @Composable () -> Unit
) {
    var permissionGranted by remember { mutableStateOf(isPermissionGranted()) }

    if(permissionGranted) {
        content()
    } else {
        RequestSinglePermission(
            permission = permission,
            onPermissionGranted = { permissionGranted = true },
            onPermissionDenied = onPermissionDenied
        )
    }
}

@Composable
fun RequestSinglePermission(
    permission: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if(it) onPermissionGranted() else onPermissionDenied()
        }
    )

    DisposableEffect(Unit) {
        launcher.launch(permission)
        onDispose {}
    }
}

@Composable
fun CheckMultiplePermissions(
    permissions: Array<String>,
    arePermissionsGranted: () -> Boolean,
    onPermissionsDenied: () -> Unit,
    content: @Composable () -> Unit
) {
    var permissionsGranted by remember { mutableStateOf(arePermissionsGranted()) }

    if(permissionsGranted) {
        content()
    } else {
        RequestMultiplePermissions(
            permissions = permissions,
            onPermissionsGranted = { permissionsGranted = true },
            onPermissionsDenied = onPermissionsDenied
        )
    }
}

@Composable
fun RequestMultiplePermissions(
    permissions: Array<String>,
    onPermissionsGranted: () -> Unit,
    onPermissionsDenied: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result: Map<String, Boolean> ->
            val arePermissionsGranted: Boolean = result.all {
                if(it.key == Manifest.permission.POST_NOTIFICATIONS) // Not mandatory
                    true
                else it.value
            }
            if(arePermissionsGranted) {
                onPermissionsGranted()
            } else {
                onPermissionsDenied()
            }
        }
    )

    DisposableEffect(Unit) {
        launcher.launch(permissions)
        onDispose {}
    }
}

@Composable
fun CheckBluetoothActivation(
    isBluetoothEnabled: Boolean,
    onBluetoothEnabledChanged: (Boolean) -> Unit
) {
    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED) { intent ->
        if(intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            (state == BluetoothAdapter.STATE_ON).let {
                if(isBluetoothEnabled != it)
                    onBluetoothEnabledChanged(it)
            }
        }
    }
}