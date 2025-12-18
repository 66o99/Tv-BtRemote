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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.common.extensions.autoMirroredIcon
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.ButtonContentTemplate
import com.atharok.btremote.ui.components.RemoteButtonSurface
import com.atharok.btremote.ui.theme.dimensionElevation2

@Composable
private fun VerticalLayout(
    contentUp: @Composable () -> Unit,
    contentDown: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
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
    topButtonTouchDown: () -> Unit,
    topIcon: ImageVector,
    @StringRes topIconDescription: Int,
    bottomButtonTouchDown: () -> Unit,
    bottomIcon: ImageVector,
    @StringRes bottomIconDescription: Int,
    buttonTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        contentUp = {
            ButtonContentTemplate(
                touchDown = topButtonTouchDown,
                touchUp = buttonTouchUp,
                shape = shape
            ) {
                Icon(
                    imageVector = topIcon,
                    contentDescription = stringResource(id = topIconDescription),
                    modifier = Modifier.autoMirroredIcon(topIcon).fillMaxSize(0.5f)
                )
            }
        },
        contentDown = {
            ButtonContentTemplate(
                touchDown = bottomButtonTouchDown,
                touchUp = buttonTouchUp,
                shape = shape
            ) {
                Icon(
                    imageVector = bottomIcon,
                    contentDescription = stringResource(id = bottomIconDescription),
                    modifier = Modifier.autoMirroredIcon(bottomIcon).fillMaxSize(0.5f)
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
    volumeUpButtonTouchDown: () -> Unit,
    volumeDownButtonTouchDown: () -> Unit,
    buttonTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        topButtonTouchDown = volumeUpButtonTouchDown,
        topIcon = AppIcons.VolumeIncrease,
        topIconDescription = R.string.volume_increase,
        bottomButtonTouchDown = volumeDownButtonTouchDown,
        bottomIcon = AppIcons.VolumeDecrease,
        bottomIconDescription = R.string.volume_decrease,
        buttonTouchUp = buttonTouchUp,
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun TVChannelVerticalButtons(
    tvChannelUpButtonTouchDown: () -> Unit,
    tvChannelDownButtonTouchDown: () -> Unit,
    buttonTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        topButtonTouchDown = tvChannelUpButtonTouchDown,
        topIcon = AppIcons.TVChannelIncrease,
        topIconDescription = R.string.next_channel,
        bottomButtonTouchDown = tvChannelDownButtonTouchDown,
        bottomIcon = AppIcons.TVChannelDecrease,
        bottomIconDescription = R.string.previous_channel,
        buttonTouchUp = buttonTouchUp,
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun BrightnessVerticalButtons(
    brightnessUpButtonTouchDown: () -> Unit,
    brightnessDownButtonTouchDown: () -> Unit,
    buttonTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    VerticalLayout(
        topButtonTouchDown = brightnessUpButtonTouchDown,
        topIcon = AppIcons.BrightnessIncrease,
        topIconDescription = R.string.brightness_increase,
        bottomButtonTouchDown = brightnessDownButtonTouchDown,
        bottomIcon = AppIcons.BrightnessDecrease,
        bottomIconDescription = R.string.brightness_decrease,
        buttonTouchUp = buttonTouchUp,
        modifier = modifier,
        shape = shape
    )
}