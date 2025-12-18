package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.common.extensions.autoMirroredIcon
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.AdaptiveText
import com.atharok.btremote.ui.components.ButtonContentTemplate
import com.atharok.btremote.ui.components.RemoteButtonSurface
import com.atharok.btremote.ui.theme.dimensionElevation2

@Composable
private fun SingleRemoteButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2(),
    content: @Composable () -> Unit
) {
    RemoteButtonSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        ButtonContentTemplate(
            touchDown = touchDown,
            touchUp = touchUp,
            shape = shape,
            content = content
        )
    }
}

@Composable
private fun IconRemoteButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    SingleRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.autoMirroredIcon(image).fillMaxSize(0.5f)
        )
    }
}

@Composable
private fun TextRemoteButton(
    text: String,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    SingleRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
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
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.Back,
        contentDescription = stringResource(id = R.string.back),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun HomeButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.Home,
        contentDescription = stringResource(id = R.string.home),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun MenuButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.Menu,
        contentDescription = stringResource(id = R.string.menu),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun PowerButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.Power,
        contentDescription = stringResource(id = R.string.power),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun VolumeMuteButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.Mute,
        contentDescription = stringResource(id = R.string.mute),
        modifier = modifier,
        shape = shape
    )
}

@Composable
fun ClosedCaptionsButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
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
    RemoteButtonSurface(
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
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "1",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton2(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "2",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton3(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "3",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton4(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "4",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton5(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "5",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton6(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "6",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton7(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "7",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton8(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "8",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton9(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "9",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelButton0(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    TextRemoteButton(
        text = "0",
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelPreviousButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.TVChannelDecrease,
        contentDescription = stringResource(id = R.string.previous_channel),
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun TVChannelNextButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = dimensionElevation2()
) {
    IconRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp,
        image = AppIcons.TVChannelIncrease,
        contentDescription = stringResource(id = R.string.next_channel),
        modifier = modifier,
        shape = shape,
        elevation = elevation
    )
}