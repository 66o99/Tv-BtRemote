package com.atharok.btremote.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.AutoConnectDropdownMenuItem
import com.atharok.btremote.ui.components.FavoriteDeviceDropdownMenuItem
import com.atharok.btremote.ui.components.MoreOverflowMenu
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormal
import com.atharok.btremote.ui.components.TextNormalSecondary
import com.atharok.btremote.ui.components.UnpairDropdownMenuItem
import com.atharok.btremote.ui.theme.dimensionElevation4

@Composable
fun DeviceItemView(
    name: String,
    macAddress: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        Image(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_min))
        ) {
            TextMedium(
                text = name,
                maxLines = 1
            )
            TextNormalSecondary(
                text = macAddress,
                maxLines = 1
            )
        }
    }
}

@Composable
fun DeviceItemView(
    name: String,
    macAddress: String,
    icon: ImageVector,
    isAutoConnectDeviceAddress: Boolean,
    autoConnect: () -> Unit,
    isFavoriteDevice: Boolean,
    onFavoriteDeviceChanged: () -> Unit,
    unpair: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DeviceItemView(
            name, macAddress, icon, Modifier.weight(1f)
        )

        if(isAutoConnectDeviceAddress) {
            Surface(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_small)
                ),
                shape = CircleShape,
                tonalElevation = dimensionElevation4()
            ) {
                TextNormal(
                    text = stringResource(id = R.string.automatic_shorten),
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_max),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    ),
                    maxLines = 1
                )
            }
        }

        Box(contentAlignment = Alignment.CenterEnd) {
            MoreOverflowMenu { closeDropdownMenu: () -> Unit ->
                AutoConnectDropdownMenuItem(
                    autoConnect = {
                        autoConnect()
                        closeDropdownMenu()
                    }
                )
                FavoriteDeviceDropdownMenuItem(
                    isFavoriteDevice = isFavoriteDevice,
                    onFavoriteDeviceChanged = {
                        onFavoriteDeviceChanged()
                        closeDropdownMenu()
                    }
                )
                UnpairDropdownMenuItem(
                    unpair = {
                        unpair()
                        closeDropdownMenu()
                    }
                )
            }
        }
    }
}