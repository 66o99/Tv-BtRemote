package com.atharok.btremote.presentation.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entities.remoteInput.RemoteInput
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationBroadcastReceiver: BroadcastReceiver(), KoinComponent {

    private val useCase: BluetoothHidServiceUseCase by inject()

    companion object {
        const val ACTION_VOLUME_INC = "action_volume_up"
        const val ACTION_VOLUME_DEC = "action_volume_down"
        const val ACTION_MULTIMEDIA_PLAY_PAUSE = "action_multimedia_play_pause"
        const val ACTION_MULTIMEDIA_PREVIOUS = "action_multimedia_previous"
        const val ACTION_MULTIMEDIA_NEXT = "action_multimedia_next"
        const val ACTION_LEFT = "action_left"
        const val ACTION_RIGHT = "action_right"
        const val ACTION_UP = "action_up"
        const val ACTION_DOWN = "action_down"
        const val ACTION_PICK = "action_pick"
        const val ACTION_BACK = "action_back"
        const val ACTION_HOME = "action_home"
        const val ACTION_DISCONNECT = "action_disconnect"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            ACTION_VOLUME_INC -> sendReport(RemoteInput.REMOTE_INPUT_VOLUME_INC)
            ACTION_VOLUME_DEC -> sendReport(RemoteInput.REMOTE_INPUT_VOLUME_DEC)
            ACTION_MULTIMEDIA_PLAY_PAUSE -> sendReport(RemoteInput.REMOTE_INPUT_PLAY_PAUSE)
            ACTION_MULTIMEDIA_PREVIOUS -> sendReport(RemoteInput.REMOTE_INPUT_PREVIOUS)
            ACTION_MULTIMEDIA_NEXT -> sendReport(RemoteInput.REMOTE_INPUT_NEXT)
            ACTION_LEFT -> sendReport(RemoteInput.REMOTE_INPUT_MENU_LEFT)
            ACTION_RIGHT -> sendReport(RemoteInput.REMOTE_INPUT_MENU_RIGHT)
            ACTION_UP -> sendReport(RemoteInput.REMOTE_INPUT_MENU_UP)
            ACTION_DOWN -> sendReport(RemoteInput.REMOTE_INPUT_MENU_DOWN)
            ACTION_PICK -> sendReport(RemoteInput.REMOTE_INPUT_MENU_PICK)
            ACTION_BACK -> sendReport(RemoteInput.REMOTE_INPUT_BACK)
            ACTION_HOME -> sendReport(RemoteInput.REMOTE_INPUT_HOME)
            ACTION_DISCONNECT -> {
                useCase.disconnectDevice()
                /*useCase.stopHidProfile()
                val serviceIntent = Intent(context, BluetoothHidService::class.java)
                context?.stopService(serviceIntent)*/
            }
        }
    }

    private fun sendReport(bytes: ByteArray) {
        useCase.sendRemoteReport(bytes)
        useCase.sendRemoteReport(REMOTE_INPUT_NONE)
    }
}