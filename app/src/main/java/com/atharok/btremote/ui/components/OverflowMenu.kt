package com.atharok.btremote.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.AppIcons.getIconModifier
import com.atharok.btremote.ui.theme.dimensionElevation2

@Composable
private fun OverflowMenu(
    item: @Composable (showMenu: () -> Unit) -> Unit,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    item { showMenu = !showMenu }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false },
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(dimensionElevation2())
    ) {
        content { showMenu = false }
    }
}

// ---- DropdownMenu ----

@Composable
fun MoreOverflowMenu(
    modifier: Modifier = Modifier,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    OverflowMenu(
        item = {
            MoreAction(
                showMenu = it,
                modifier = modifier
            )
        },
        content = content
    )
}

@Composable
fun BluetoothPairingOverflowMenu(
    modifier: Modifier = Modifier,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    OverflowMenu(
        item = {
            PairingNewDeviceAction(
                onClick = it,
                modifier = modifier
            )
        },
        content = content
    )
}

// ---- DropdownMenuItem ----

@Composable
private fun DropdownMenuItemTemplate(
    onClick: () -> Unit,
    image: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            TextNormal(title)
        },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = image,
                contentDescription = title,
                modifier = getIconModifier(image)
            )
        }
    )
}

@Composable
private fun DropdownMenuItemTemplate(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    image: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    StatefulRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp
    ) {
        DropdownMenuItem(
            text = {
                TextNormal(title)
            },
            onClick = {},
            modifier = modifier,
            leadingIcon = {
                Icon(
                    imageVector = image,
                    contentDescription = title
                )
            },
            interactionSource = it
        )
    }
}

@Composable
fun BrightnessIncDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.BrightnessIncrease,
        title = stringResource(id = R.string.brightness_increase),
        modifier = modifier
    )
}

@Composable
fun BrightnessDecDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.BrightnessDecrease,
        title = stringResource(id = R.string.brightness_decrease),
        modifier = modifier
    )
}

@Composable
fun DisconnectDropdownMenuItem(
    disconnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = disconnect,
        image = AppIcons.Disconnect,
        title = stringResource(id = R.string.disconnect),
        modifier = modifier
    )
}

@Composable
fun HelpDropdownMenuItem(
    showHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = showHelp,
        image = AppIcons.Help,
        title = stringResource(id = R.string.help),
        modifier = modifier
    )
}

@Composable
fun SettingsDropdownMenuItem(
    showSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = showSettingsScreen,
        image = AppIcons.Settings,
        title = stringResource(id = R.string.settings),
        modifier = modifier
    )
}

@Composable
fun UnpairDropdownMenuItem(
    unpair: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = unpair,
        image = AppIcons.BluetoothUnpair,
        title = stringResource(id = R.string.unpair),
        modifier = modifier
    )
}

@Composable
fun AutoConnectDropdownMenuItem(
    autoConnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = autoConnect,
        image = AppIcons.EnabledAutoConnect,
        title = stringResource(id = R.string.automatic_connect),
        modifier = modifier
    )
}

@Composable
fun FavoriteDeviceDropdownMenuItem(
    isFavoriteDevice: Boolean,
    onFavoriteDeviceChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = onFavoriteDeviceChanged,
        image = AppIcons.Favorite,
        title = stringResource(
            id = if(isFavoriteDevice) R.string.remove_from_favorites else R.string.add_to_favorites
        ),
        modifier = modifier
    )
}

@Composable
fun BluetoothPairingFromAScannedDeviceDropdownMenuItem(
    openBluetoothPairingScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = openBluetoothPairingScreen,
        image = AppIcons.BluetoothPairing,
        title = stringResource(id = R.string.pairing_a_device),
        modifier = modifier
    )
}

@Composable
fun BluetoothPairingFromARemoteDeviceDropdownMenuItem(
    openBluetoothPairingScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = openBluetoothPairingScreen,
        image = AppIcons.BluetoothPairing,
        title = stringResource(id = R.string.pairing_from_the_remote_device),
        modifier = modifier
    )
}

@Composable
fun EnterBluetoothAddressManuallyDropdownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItemTemplate(
        onClick = onClick,
        image = AppIcons.Keyboard,
        title = stringResource(id = R.string.enter_bluetooth_address_manually),
        modifier = modifier
    )
}