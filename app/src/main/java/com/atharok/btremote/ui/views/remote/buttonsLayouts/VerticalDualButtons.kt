package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.AppIcons.getIconModifier
import com.atharok.btremote.domain.entity.remoteInput.RemoteInput
import com.atharok.btremote.ui.components.RemoteButtonContentTemplate
import com.atharok.btremote.ui.components.RemoteButtonSurface

@Composable
private fun VerticalLayout(
    contentUp: @Composable () -> Unit,
    contentDown: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    RemoteButtonSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.aspectRatio(1f).weight(1f, false)) {
                contentUp()
            }
            Box(modifier = Modifier.aspectRatio(1f).weight(1f, false)) {
                contentDown()
            }
        }
    }
}

@Composable
private fun VerticalLayout(
    upBytes: ByteArray,
    upIcon: ImageVector,
    @StringRes upIconDescription: Int,
    downBytes: ByteArray,
    downIcon: ImageVector,
    @StringRes downIconDescription: Int,
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        contentUp = {
            RemoteButtonContentTemplate(
                bytes = upBytes,
                sendReport = sendReport,
                shape = shape
            ) {
                Icon(
                    imageVector = upIcon,
                    contentDescription = stringResource(id = upIconDescription),
                    modifier = getIconModifier(upIcon).fillMaxSize(0.5f)
                )
            }
        },
        contentDown = {
            RemoteButtonContentTemplate(
                bytes = downBytes,
                sendReport = sendReport,
                shape = shape
            ) {
                Icon(
                    imageVector = downIcon,
                    contentDescription = stringResource(id = downIconDescription),
                    modifier = getIconModifier(downIcon).fillMaxSize(0.5f)
                )
            }
        },
        modifier = modifier,
        shape = shape
    )
}

// ---- Specific ----

@Composable
fun VolumeVerticalButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        upBytes = RemoteInput.REMOTE_INPUT_VOLUME_INC,
        upIcon = AppIcons.VolumeIncrease,
        upIconDescription = R.string.volume_increase,
        downBytes = RemoteInput.REMOTE_INPUT_VOLUME_DEC,
        downIcon = AppIcons.VolumeDecrease,
        downIconDescription = R.string.volume_decrease,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun TVChannelVerticalButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        upBytes = RemoteInput.REMOTE_INPUT_CHANNEL_INC,
        upIcon = AppIcons.TVChannelIncrease,
        upIconDescription = R.string.next_channel,
        downBytes = RemoteInput.REMOTE_INPUT_CHANNEL_DEC,
        downIcon = AppIcons.TVChannelDecrease,
        downIconDescription = R.string.previous_channel,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun BrightnessVerticalButtons(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        upBytes = RemoteInput.REMOTE_INPUT_BRIGHTNESS_INC,
        upIcon = AppIcons.BrightnessIncrease,
        upIconDescription = R.string.brightness_increase,
        downBytes = RemoteInput.REMOTE_INPUT_BRIGHTNESS_DEC,
        downIcon = AppIcons.BrightnessDecrease,
        downIconDescription = R.string.brightness_decrease,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape
    )
}