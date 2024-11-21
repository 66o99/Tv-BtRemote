package com.atharok.btremote.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons

// ---- Actions ----

@Composable
private fun TopAppBarAction(
    onClick: () -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun NavigateUpAction(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = navigateUp,
        image = AppIcons.Back,
        contentDescription = stringResource(id = R.string.back),
        modifier = modifier
    )
}

@Composable
fun SettingsAction(
    openSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = openSettingsScreen,
        image = AppIcons.Settings,
        contentDescription = stringResource(id = R.string.settings),
        modifier = modifier
    )
}

@Composable
fun PairingNewDeviceAction(
    openBluetoothPairingScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = openBluetoothPairingScreen,
        image = AppIcons.BluetoothPairing,
        contentDescription = stringResource(id = R.string.pairing_a_device),
        modifier = modifier
    )
}

@Composable
fun HelpAction(
    showHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showHelp,
        image = AppIcons.Help,
        contentDescription = stringResource(id = R.string.help),
        modifier = modifier
    )
}

@Composable
fun RefreshAction(
    refresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = refresh,
        image = AppIcons.Refresh,
        contentDescription = stringResource(id = R.string.refresh),
        modifier = modifier
    )
}

@Composable
fun KeyboardAction(
    showKeyboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showKeyboard,
        image = AppIcons.Keyboard,
        contentDescription = stringResource(id = R.string.keyboard),
        modifier = modifier
    )
}

@Composable
fun DirectionButtonsAction(
    showDirectionButtons: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showDirectionButtons,
        image = AppIcons.Controller,
        contentDescription = stringResource(id = R.string.direction_arrows),
        modifier = modifier
    )
}

@Composable
fun MouseAction(
    showMousePad: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showMousePad,
        image = AppIcons.Mouse,
        contentDescription = stringResource(id = R.string.mouse),
        modifier = modifier
    )
}

@Composable
fun MoreAction(
    showMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBarAction(
        onClick = showMenu,
        image = AppIcons.MoreVert,
        contentDescription = stringResource(id = R.string.more),
        modifier = modifier
    )
}