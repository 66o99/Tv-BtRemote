package com.atharok.btremote.ui.views.remote.buttonsLayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.TemplateDialog
import com.atharok.btremote.ui.components.TextLarge
import com.atharok.btremote.ui.theme.dimensionElevation4

@Composable
fun TVChannelDialog(
    tvChannelIncTouchDown: () -> Unit,
    tvChannelDecTouchDown: () -> Unit,
    tvChannel1TouchDown: () -> Unit,
    tvChannel2TouchDown: () -> Unit,
    tvChannel3TouchDown: () -> Unit,
    tvChannel4TouchDown: () -> Unit,
    tvChannel5TouchDown: () -> Unit,
    tvChannel6TouchDown: () -> Unit,
    tvChannel7TouchDown: () -> Unit,
    tvChannel8TouchDown: () -> Unit,
    tvChannel9TouchDown: () -> Unit,
    tvChannel0TouchDown: () -> Unit,
    remoteTouchUp: () -> Unit,
    keyboardTouchUp: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    TemplateDialog(
        title = {
            TextLarge(text = stringResource(R.string.tv))
        },
        content = {
            TVChannelLayout(
                tvChannelIncTouchDown = tvChannelIncTouchDown,
                tvChannelDecTouchDown = tvChannelDecTouchDown,
                tvChannel1TouchDown = tvChannel1TouchDown,
                tvChannel2TouchDown = tvChannel2TouchDown,
                tvChannel3TouchDown = tvChannel3TouchDown,
                tvChannel4TouchDown = tvChannel4TouchDown,
                tvChannel5TouchDown = tvChannel5TouchDown,
                tvChannel6TouchDown = tvChannel6TouchDown,
                tvChannel7TouchDown = tvChannel7TouchDown,
                tvChannel8TouchDown = tvChannel8TouchDown,
                tvChannel9TouchDown = tvChannel9TouchDown,
                tvChannel0TouchDown = tvChannel0TouchDown,
                remoteTouchUp = remoteTouchUp,
                keyboardTouchUp = keyboardTouchUp,
                modifier = Modifier.aspectRatio(3f / 4f)
            )
        },
        modifier = modifier,
        dismissButtonText = stringResource(R.string.close),
        onDismissRequest = onDismissRequest
    )
}

@Composable
private fun TVChannelLayout(
    tvChannelIncTouchDown: () -> Unit,
    tvChannelDecTouchDown: () -> Unit,
    tvChannel1TouchDown: () -> Unit,
    tvChannel2TouchDown: () -> Unit,
    tvChannel3TouchDown: () -> Unit,
    tvChannel4TouchDown: () -> Unit,
    tvChannel5TouchDown: () -> Unit,
    tvChannel6TouchDown: () -> Unit,
    tvChannel7TouchDown: () -> Unit,
    tvChannel8TouchDown: () -> Unit,
    tvChannel9TouchDown: () -> Unit,
    tvChannel0TouchDown: () -> Unit,
    remoteTouchUp: () -> Unit,
    keyboardTouchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    val elevation = dimensionElevation4()
    val padding: Dp = dimensionResource(id = R.dimen.padding_normal)

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            TVChannelButton1(
                touchDown = tvChannel1TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton2(
                touchDown = tvChannel2TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton3(
                touchDown = tvChannel3TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            TVChannelButton4(
                touchDown = tvChannel4TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton5(
                touchDown = tvChannel5TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton6(
                touchDown = tvChannel6TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            TVChannelButton7(
                touchDown = tvChannel7TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton8(
                touchDown = tvChannel8TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton9(
                touchDown = tvChannel9TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            TVChannelPreviousButton(
                touchDown = tvChannelDecTouchDown,
                touchUp = remoteTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton0(
                touchDown = tvChannel0TouchDown,
                touchUp = keyboardTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelNextButton(
                touchDown = tvChannelIncTouchDown,
                touchUp = remoteTouchUp,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
        }
    }
}