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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.views.remoteButtons.StatefulRemoteButton

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
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(dimensionResource(id = R.dimen.elevation_2))
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
                contentDescription = title
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