package com.atharok.btremote.ui.views.remote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.ui.views.remote.buttonsLayouts.BackButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.BrightnessVerticalButtons
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton0
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton1
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton2
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton3
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton4
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton5
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton6
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton7
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton8
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelButton9
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ChannelVerticalButtons
import com.atharok.btremote.ui.views.remote.buttonsLayouts.ClosedCaptionsButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.HomeButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.MenuButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.MultimediaLayout
import com.atharok.btremote.ui.views.remote.buttonsLayouts.PowerButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.VolumeMuteButton
import com.atharok.btremote.ui.views.remote.buttonsLayouts.VolumeVerticalButtons

@Composable
fun RemoteView(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    val padding: Dp = dimensionResource(id = R.dimen.remote_button_padding)

    Column(modifier = modifier) {
        MultimediaLayout(
            sendReport = sendRemoteKeyReport,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(padding),
            shape = shape
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                VolumeVerticalButtons(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(2f)
                        .padding(padding)
                        .align(Alignment.Start),
                    shape = shape
                )

                VolumeMuteButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                BackButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                ChannelButton1(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton4(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton7(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                HomeButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                ChannelButton2(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton5(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton8(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton0(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                ChannelButton3(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton6(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                ChannelButton9(
                    sendReport = sendNumberKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                MenuButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                ChannelVerticalButtons(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(2f)
                        .padding(padding)
                        .align(Alignment.End),
                    shape = shape
                )

                ClosedCaptionsButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

                PowerButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )
            }
        }
    }
}

@Composable
fun MinimalistRemoteView(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    val padding: Dp = dimensionResource(id = R.dimen.remote_button_padding)

    Column(modifier = modifier) {
        MultimediaLayout(
            sendReport = sendRemoteKeyReport,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(padding),
            shape = shape
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f).padding(padding)) {
                VolumeVerticalButtons(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier.align(Alignment.Start),
                    shape = shape
                )
            }

            Column(
                modifier = Modifier.weight(2f)
            ) {

                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    VolumeMuteButton(
                        sendReport = sendRemoteKeyReport,
                        modifier = Modifier
                            .weight(1f)
                            .padding(padding),
                        shape = shape
                    )

                    ClosedCaptionsButton(
                        sendReport = sendRemoteKeyReport,
                        modifier = Modifier
                            .weight(1f)
                            .padding(padding),
                        shape = shape
                    )
                }

                HomeButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(padding),
                    shape = shape
                )

            }

            Column(modifier = Modifier.weight(1f).padding(padding)) {
                BrightnessVerticalButtons(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier.align(Alignment.End),
                    shape = shape
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            BackButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(2f)
                    .padding(padding),
                shape = shape
            )
            MenuButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape
            )
            PowerButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(padding),
                shape = shape
            )
        }
    }
}