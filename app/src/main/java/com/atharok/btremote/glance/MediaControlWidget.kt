package com.atharok.btremote.glance

import android.bluetooth.BluetoothHidDevice
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionSendBroadcast
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import com.atharok.btremote.R
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.presentation.activities.MainActivity
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_NEXT
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_PLAY_PAUSE
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_PREVIOUS
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_VOLUME_DEC
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_VOLUME_INC
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MediaControlWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MediaControlWidget()
}

class MediaControlWidget : GlanceAppWidget(), KoinComponent {
    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val bluetoothHidServiceUseCase by inject<BluetoothHidServiceUseCase>()
        provideContent {
            GlanceTheme {
                val dimensions = MediaControlWidgetDimensions.calculateDimensions(
                    height = LocalSize.current.height
                )
                val state by bluetoothHidServiceUseCase.getDeviceHidConnectionState().collectAsState()
                if (state.state != BluetoothHidDevice.STATE_CONNECTED) {
                    AppLauncher(
                        dimensions = dimensions,
                        context = context
                    )
                } else {
                    MediaControls(
                        deviceName = state.deviceName,
                        dimensions = dimensions,
                        context = context
                    )
                }
            }
        }
    }

    @Composable
    private fun AppLauncher(
        dimensions: MediaControlWidgetDimensions,
        context: Context = LocalContext.current
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(dimensions.padding)
                .background(GlanceTheme.colors.widgetBackground),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            GlanceWidgetTitle(
                appName = context.getString(R.string.app_name),
                connectionState = context.getString(R.string.connected_off),
                dimensions = dimensions
            )
            Spacer(GlanceModifier.defaultWeight())
            Button(
                text = context.getString(R.string.connect),
                onClick = actionStartActivity(
                    intent = getStartActivityIntent(context)
                ),
                modifier = GlanceModifier.fillMaxWidth(),
                maxLines = 1
            )
        }
    }

    @Composable
    private fun MediaControls(
        deviceName: String,
        dimensions: MediaControlWidgetDimensions,
        context: Context = LocalContext.current
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(dimensions.padding)
                .background(GlanceTheme.colors.widgetBackground),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.Top
            ) {

                GlanceWidgetTitle(
                    appName = context.getString(R.string.app_name),
                    connectionState = context.getString(R.string.connected_on, deviceName),
                    dimensions = dimensions,
                    modifier = GlanceModifier.defaultWeight()
                )

                CircleIconButton(
                    imageProvider = ImageProvider(R.drawable.round_open_in_full_24),
                    contentDescription = context.getString(R.string.open),
                    onClick = actionStartActivity(
                        intent = getStartActivityIntent(context)
                    ),
                    modifier = GlanceModifier.size(dimensions.minIconSize),
                    backgroundColor = GlanceTheme.colors.primary,
                    contentColor = GlanceTheme.colors.onPrimary
                )
            }

            Spacer(GlanceModifier.defaultWeight())

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.Bottom
            ) {

                // Multimedia

                GlanceCustomIconButton(
                    imageProvider = ImageProvider(R.drawable.round_skip_previous_24),
                    contentDescription = context.getString(R.string.previous),
                    onClick = mediaControlAction(context, ACTION_MULTIMEDIA_PREVIOUS),
                    rippleOverride = R.drawable.glance_button_ripple_left,
                    backgroundDrawable = R.drawable.glance_button_shape_left,
                    dimensions = dimensions
                )
                Spacer(GlanceModifier.width(2.dp))
                GlanceCustomIconButton(
                    imageProvider = ImageProvider(R.drawable.round_play_pause_24),
                    contentDescription = context.getString(R.string.play),
                    onClick = mediaControlAction(context, ACTION_MULTIMEDIA_PLAY_PAUSE),
                    rippleOverride = R.drawable.glance_button_ripple_middle,
                    backgroundDrawable = R.drawable.glance_button_shape_middle,
                    dimensions = dimensions
                )
                Spacer(GlanceModifier.width(2.dp))
                GlanceCustomIconButton(
                    imageProvider = ImageProvider(R.drawable.round_skip_next_24),
                    contentDescription = context.getString(R.string.next),
                    onClick = mediaControlAction(context, ACTION_MULTIMEDIA_NEXT),
                    rippleOverride = R.drawable.glance_button_ripple_right,
                    backgroundDrawable = R.drawable.glance_button_shape_right,
                    dimensions = dimensions
                )

                // Separator

                Spacer(GlanceModifier.defaultWeight())

                // Audio

                GlanceCustomIconButton(
                    imageProvider = ImageProvider(R.drawable.round_volume_down_24),
                    contentDescription = context.getString(R.string.volume_decrease),
                    onClick = mediaControlAction(context, ACTION_VOLUME_DEC),
                    rippleOverride = R.drawable.glance_button_ripple_left,
                    backgroundDrawable = R.drawable.glance_button_shape_left,
                    backgroundColor = GlanceTheme.colors.tertiary,
                    contentColor = GlanceTheme.colors.onTertiary,
                    dimensions = dimensions
                )
                Spacer(GlanceModifier.width(2.dp))
                GlanceCustomIconButton(
                    imageProvider = ImageProvider(R.drawable.round_volume_up_24),
                    contentDescription = context.getString(R.string.volume_increase),
                    onClick = mediaControlAction(context, ACTION_VOLUME_INC),
                    rippleOverride = R.drawable.glance_button_ripple_right,
                    backgroundDrawable = R.drawable.glance_button_shape_right,
                    backgroundColor = GlanceTheme.colors.tertiary,
                    contentColor = GlanceTheme.colors.onTertiary,
                    dimensions = dimensions
                )
            }
        }
    }

    private fun getStartActivityIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun mediaControlAction(context: Context, action: String) =
        actionSendBroadcast(Intent(context, NotificationBroadcastReceiver::class.java).apply { setAction(action) })
}
