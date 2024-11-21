package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entity.remoteInput.ChannelInput
import com.atharok.btremote.domain.entity.remoteInput.RemoteInput
import com.atharok.btremote.ui.components.AdaptiveText
import com.atharok.btremote.ui.views.remoteButtons.ButtonContentTemplate
import com.atharok.btremote.ui.views.remoteButtons.RemoteButtonContentTemplate
import com.atharok.btremote.ui.views.remoteButtons.RemoteButtonSkin

@Composable
private fun SingleRemoteButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1),
    content: @Composable () -> Unit
) {
    RemoteButtonSkin(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        RemoteButtonContentTemplate(
            bytes = bytes,
            sendReport = sendReport,
            shape = shape,
            content = content
        )
    }
}

@Composable
private fun IconRemoteButton(
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    SingleRemoteButton(
        bytes = bytes,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}

@Composable
private fun TextRemoteButton(
    text: String,
    bytes: ByteArray,
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    SingleRemoteButton(
        bytes = bytes,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        AdaptiveText(
            text = text,
            percent = 0.45f,
            modifier = Modifier.fillMaxSize(),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

// ---- Specific ----

@Composable
fun BackButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_BACK,
        sendReport = sendReport,
        image = AppIcons.Back,
        contentDescription = stringResource(id = R.string.back),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun HomeButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_HOME,
        sendReport = sendReport,
        image = AppIcons.Home,
        contentDescription = stringResource(id = R.string.home),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun MenuButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_MENU,
        sendReport = sendReport,
        image = AppIcons.Menu,
        contentDescription = stringResource(id = R.string.menu),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun PowerButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_POWER,
        sendReport = sendReport,
        image = AppIcons.Power,
        contentDescription = stringResource(id = R.string.power),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun VolumeMuteButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_VOLUME_MUTE,
        sendReport = sendReport,
        image = AppIcons.Mute,
        contentDescription = stringResource(id = R.string.mute),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun ClosedCaptionsButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_CLOSED_CAPTIONS,
        sendReport = sendReport,
        image = AppIcons.ClosedCaption,
        contentDescription = stringResource(id = R.string.closed_captions),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun TVChannelButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    RemoteButtonSkin(
        modifier = modifier,
        shape = shape
    ) {
        ButtonContentTemplate(
            touchDown = touchDown,
            touchUp = touchUp,
            shape = shape,
            content = {
                Icon(
                    imageVector = AppIcons.TVChannel,
                    contentDescription = stringResource(id = R.string.tv),
                    modifier = Modifier.fillMaxSize(0.5f)
                )
            }
        )
    }
}

// ---- Channel ----

@Composable
fun TVChannelButton1(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "1",
        bytes = ChannelInput.CHANNEL_INPUT_1,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton2(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "2",
        bytes = ChannelInput.CHANNEL_INPUT_2,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton3(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "3",
        bytes = ChannelInput.CHANNEL_INPUT_3,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton4(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "4",
        bytes = ChannelInput.CHANNEL_INPUT_4,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton5(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "5",
        bytes = ChannelInput.CHANNEL_INPUT_5,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton6(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "6",
        bytes = ChannelInput.CHANNEL_INPUT_6,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton7(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "7",
        bytes = ChannelInput.CHANNEL_INPUT_7,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton8(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "8",
        bytes = ChannelInput.CHANNEL_INPUT_8,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton9(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "9",
        bytes = ChannelInput.CHANNEL_INPUT_9,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton0(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    TextRemoteButton(
        text = "0",
        bytes = ChannelInput.CHANNEL_INPUT_0,
        sendReport = sendReport,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelPreviousButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_CHANNEL_DEC,
        sendReport = sendReport,
        image = AppIcons.TVChannelDecrease,
        contentDescription = stringResource(id = R.string.previous_channel),
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelNextButton(
    sendReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionResource(id = R.dimen.elevation_1)
) {
    IconRemoteButton(
        bytes = RemoteInput.REMOTE_INPUT_CHANNEL_INC,
        sendReport = sendReport,
        image = AppIcons.TVChannelIncrease,
        contentDescription = stringResource(id = R.string.next_channel),
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}