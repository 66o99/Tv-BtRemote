package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.remoteInput.RemoteInput
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.views.remoteButtons.RemoteButtonContentTemplate

@Composable
private fun MultimediaLayout(
    previous: @Composable () -> Unit,
    playPause: @Composable () -> Unit,
    next: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
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
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    MultimediaLayout(
        previous = {
            MultimediaPreviousButtonContent(sendReport = sendReport, shape = shape)
        },
        playPause = {
            MultimediaPlayPauseButtonContent(sendReport = sendReport, shape = shape)
        },
        next = {
            MultimediaNextButtonContent(sendReport = sendReport, shape = shape)
        },
        modifier = modifier,
        shape = shape
    )
}

@Composable
private fun MultimediaPreviousButtonContent(
    sendReport: (ByteArray) -> Unit,
    shape: Shape
) {
    RemoteButtonContentTemplate(
        bytes = RemoteInput.REMOTE_INPUT_PREVIOUS,
        sendReport = sendReport,
        shape = shape
    ) {
        Icon(
            imageVector = Icons.Rounded.SkipPrevious,
            contentDescription = stringResource(id = R.string.previous),
            modifier = Modifier.fillMaxSize(0.65f)
        )
    }
}

@Composable
private fun MultimediaNextButtonContent(
    sendReport: (ByteArray) -> Unit,
    shape: Shape
) {
    RemoteButtonContentTemplate(
        bytes = RemoteInput.REMOTE_INPUT_NEXT,
        sendReport = sendReport,
        shape = shape
    ) {
        Icon(
            imageVector = Icons.Rounded.SkipNext,
            contentDescription = stringResource(id = R.string.next),
            modifier = Modifier.fillMaxSize(0.65f)
        )
    }
}

@Composable
private fun MultimediaPlayPauseButtonContent(
    sendReport: (ByteArray) -> Unit,
    shape: Shape
) {
    RemoteButtonContentTemplate(
        bytes = RemoteInput.REMOTE_INPUT_PLAY_PAUSE,
        sendReport = sendReport,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(0.65f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = stringResource(id = R.string.play),
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            )
            Icon(
                imageVector = Icons.Rounded.Pause,
                contentDescription = stringResource(id = R.string.pause),
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            )
        }
    }
}