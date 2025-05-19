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
import com.atharok.btremote.ui.theme.dimensionElevation3

@Composable
fun TVChannelDialog(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    TemplateDialog(
        title = {
            TextLarge(text = stringResource(R.string.tv))
        },
        content = {
            TVChannelLayout(
                sendRemoteKeyReport = sendRemoteKeyReport,
                sendNumberKeyReport = sendNumberKeyReport,
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
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    val elevation = dimensionElevation3()
    val padding: Dp = dimensionResource(id = R.dimen.padding_normal)

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            TVChannelButton1(
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton2(
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton3(
                sendReport = sendNumberKeyReport,
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
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton5(
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton6(
                sendReport = sendNumberKeyReport,
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
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton8(
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton9(
                sendReport = sendNumberKeyReport,
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
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelButton0(
                sendReport = sendNumberKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
            TVChannelNextButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape,
                elevation = elevation
            )
        }
    }
}