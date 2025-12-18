package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.ButtonContentTemplate
import com.atharok.btremote.ui.components.RemoteButtonSurface
import com.atharok.btremote.ui.theme.dimensionElevation2

@Composable
private fun MultimediaLayout(
    previous: @Composable () -> Unit,
    playPause: @Composable () -> Unit,
    next: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    RemoteButtonSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                previous()
            }
            Box(modifier = Modifier.weight(2f)) {
                playPause()
            }
            Box(modifier = Modifier.weight(1f)) {
                next()
            }
        }
    }
}

// ---- Specific ----

@Composable
fun MultimediaLayout(
    multimediaPlayPauseTouchDown: () -> Unit,
    multimediaPreviousTouchDown: () -> Unit,
    multimediaNextTouchDown: () -> Unit,
    remoteTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    MultimediaLayout(
        previous = {
            MultimediaPreviousButtonContent(
                touchDown = multimediaPreviousTouchDown,
                touchUp = remoteTouchUp,
                shape = shape
            )
        },
        playPause = {
            MultimediaPlayPauseButtonContent(
                touchDown = multimediaPlayPauseTouchDown,
                touchUp = remoteTouchUp,
                shape = shape
            )
        },
        next = {
            MultimediaNextButtonContent(
                touchDown = multimediaNextTouchDown,
                touchUp = remoteTouchUp,
                shape = shape
            )
        },
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
private fun MultimediaPreviousButtonContent(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    shape: Shape
) {
    ButtonContentTemplate(
        touchDown = touchDown,
        touchUp = touchUp,
        shape = shape
    ) {
        Icon(
            imageVector = AppIcons.MultimediaPrevious,
            contentDescription = stringResource(id = R.string.previous),
            modifier = Modifier.fillMaxSize(0.65f)
        )
    }
}

@Composable
private fun MultimediaNextButtonContent(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    shape: Shape
) {
    ButtonContentTemplate(
        touchDown = touchDown,
        touchUp = touchUp,
        shape = shape
    ) {
        Icon(
            imageVector = AppIcons.MultimediaNext,
            contentDescription = stringResource(id = R.string.next),
            modifier = Modifier.fillMaxSize(0.65f)
        )
    }
}

@Composable
private fun MultimediaPlayPauseButtonContent(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    shape: Shape
) {
    ButtonContentTemplate(
        touchDown = touchDown,
        touchUp = touchUp,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(0.65f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.MultimediaPlay,
                contentDescription = stringResource(id = R.string.play),
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            )
            Icon(
                imageVector = AppIcons.MultimediaPause,
                contentDescription = stringResource(id = R.string.pause),
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            )
        }
    }
}