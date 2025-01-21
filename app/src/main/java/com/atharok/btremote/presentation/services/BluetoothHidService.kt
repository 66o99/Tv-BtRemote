package com.atharok.btremote.presentation.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothProfile.STATE_CONNECTED
import android.bluetooth.BluetoothProfile.STATE_DISCONNECTED
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.NOTIFICATION_CHANNEL_ID
import com.atharok.btremote.common.utils.NOTIFICATION_ID
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.presentation.activities.MainActivity
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_BACK
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_DISCONNECT
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_DOWN
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_HOME
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_LEFT
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_NEXT
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_PLAY_PAUSE
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_MULTIMEDIA_PREVIOUS
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_PICK
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_RIGHT
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_UP
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_VOLUME_DEC
import com.atharok.btremote.presentation.services.NotificationBroadcastReceiver.Companion.ACTION_VOLUME_INC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class BluetoothHidService : Service() {

    private val useCase: BluetoothHidServiceUseCase by inject()
    private lateinit var notificationManager: NotificationManager
    private var job: Job? = null
    private var currentConnectionState = STATE_DISCONNECTED

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        registerReceiver()

        job = CoroutineScope(Dispatchers.Main).launch {
            useCase.getDeviceHidConnectionState().collect {
                when(it.state) {
                    STATE_CONNECTED -> {
                        if(currentConnectionState != STATE_CONNECTED) {
                            updateNotificationForConnectedState(it.deviceName)
                            currentConnectionState = STATE_CONNECTED
                        }
                    }
                    STATE_DISCONNECTED -> {
                        if(currentConnectionState != STATE_DISCONNECTED) {
                            updateNotificationForDisconnectedState()
                            currentConnectionState = STATE_DISCONNECTED
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        stopBluetoothHidProfile()
        job?.cancel()
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopBluetoothHidProfile()
        stopSelf()
        super.onTaskRemoved(rootIntent)
    }

    override fun onBind(intent: Intent?): IBinder = Binder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            startForeground(NOTIFICATION_ID, createNotification())
            startBluetoothHidProfile()
        } catch (exception: Exception) {
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    // ---- Notification ----

    private fun createNotificationChannel() {
        notificationManager = getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_LOW
        )
        channel.enableVibration(false)
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.connected_off))
            .setOngoing(true)
            //.setContentIntent(createOpenApplicationPendingIntent())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.round_launch_24,
                    getString(R.string.open),
                    createOpenApplicationPendingIntent()
                ).build()
            )
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setVibrate(longArrayOf(0))
            .build()
    }

    private fun updateNotificationForConnectedState(deviceName: String) {
        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.connected_on, deviceName))
            .setOngoing(true)
            //.setContentIntent(createOpenApplicationPendingIntent())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(configureRemoteViews())
            .addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.round_launch_24,
                    getString(R.string.open),
                    createOpenApplicationPendingIntent()
                ).build()
            )
            .addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.round_link_off_24,
                    getString(R.string.disconnect),
                    createPendingIntent(ACTION_DISCONNECT)
                ).build()
            )
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setVibrate(longArrayOf(0))
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun updateNotificationForDisconnectedState() {
        val notification = createNotification()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun configureRemoteViews(): RemoteViews {
        return RemoteViews(packageName, R.layout.notification_large_layout).apply {
            this.setOnClickPendingIntent(R.id.notification_large_layout_volume_inc, createPendingIntent(ACTION_VOLUME_INC))
            this.setOnClickPendingIntent(R.id.notification_large_layout_volume_dec, createPendingIntent(ACTION_VOLUME_DEC))
            this.setOnClickPendingIntent(R.id.notification_large_layout_play_pause, createPendingIntent(ACTION_MULTIMEDIA_PLAY_PAUSE))
            this.setOnClickPendingIntent(R.id.notification_large_layout_previous, createPendingIntent(ACTION_MULTIMEDIA_PREVIOUS))
            this.setOnClickPendingIntent(R.id.notification_large_layout_next, createPendingIntent(ACTION_MULTIMEDIA_NEXT))
            this.setOnClickPendingIntent(R.id.notification_large_layout_left, createPendingIntent(ACTION_LEFT))
            this.setOnClickPendingIntent(R.id.notification_large_layout_right, createPendingIntent(ACTION_RIGHT))
            this.setOnClickPendingIntent(R.id.notification_large_layout_up, createPendingIntent(ACTION_UP))
            this.setOnClickPendingIntent(R.id.notification_large_layout_down, createPendingIntent(ACTION_DOWN))
            this.setOnClickPendingIntent(R.id.notification_large_layout_pick, createPendingIntent(ACTION_PICK))
            this.setOnClickPendingIntent(R.id.notification_large_layout_back, createPendingIntent(ACTION_BACK))
            this.setOnClickPendingIntent(R.id.notification_large_layout_home, createPendingIntent(ACTION_HOME))
        }
    }

    private fun createPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, NotificationBroadcastReceiver::class.java).apply {
            this.setAction(action)
        }
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun createOpenApplicationPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    // ---- Bluetooth HID Profile ----

    private fun startBluetoothHidProfile() {
        useCase.startHidProfile()
    }

    private fun stopBluetoothHidProfile() {
        useCase.stopHidProfile()
        unregisterReceiver()
    }

    // ---- BroadcastReceiver ----

    private val bluetoothStateChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    if (bluetoothState == BluetoothAdapter.STATE_OFF) {
                        stopBluetoothHidProfile()
                        stopSelf() // Stop service if bluetooth is disabled
                    }
                }
            }
        }
    }

    private fun registerReceiver() {
        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(bluetoothStateChangeReceiver, filter)
    }

    private fun unregisterReceiver() {
        try {
            unregisterReceiver(bluetoothStateChangeReceiver)
        } catch (e: java.lang.RuntimeException) {
            Log.i("unregisterReceiver()", "Receiver already unregister")
        }
    }
}