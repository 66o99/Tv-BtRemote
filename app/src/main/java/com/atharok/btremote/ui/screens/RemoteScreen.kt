package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
import android.content.Context
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.common.utils.getKeyboardLayout
import com.atharok.btremote.domain.entities.DeviceHidConnectionState
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.ChannelInput
import com.atharok.btremote.domain.entities.remoteInput.MouseAction
import com.atharok.btremote.domain.entities.remoteInput.RemoteInput
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardKey
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.presentation.services.BluetoothHidService
import com.atharok.btremote.presentation.viewmodel.RemoteViewModel
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.BrightnessDecDropdownMenuItem
import com.atharok.btremote.ui.components.BrightnessIncDropdownMenuItem
import com.atharok.btremote.ui.components.DirectionButtonsAction
import com.atharok.btremote.ui.components.DisconnectDropdownMenuItem
import com.atharok.btremote.ui.components.FadeAnimatedContent
import com.atharok.btremote.ui.components.HelpDropdownMenuItem
import com.atharok.btremote.ui.components.KeyboardAction
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.MoreOverflowMenu
import com.atharok.btremote.ui.components.MouseAction
import com.atharok.btremote.ui.components.RemoteAction
import com.atharok.btremote.ui.components.SettingsDropdownMenuItem
import com.atharok.btremote.ui.theme.surfaceElevationMedium
import com.atharok.btremote.ui.views.RemoteScreenHelpModalBottomSheet
import com.atharok.btremote.ui.views.keyboard.AdvancedKeyboard
import com.atharok.btremote.ui.views.keyboard.AdvancedKeyboardModalBottomSheet
import com.atharok.btremote.ui.views.keyboard.VirtualKeyboardModalBottomSheet
import com.atharok.btremote.ui.views.mouse.MousePadLayout
import com.atharok.btremote.ui.views.remote.MinimalistRemoteView
import com.atharok.btremote.ui.views.remote.RemoteView
import com.atharok.btremote.ui.views.remote.buttonsLayouts.TVChannelDialog
import com.atharok.btremote.ui.views.remoteNavigation.RemoteDirectionalPadNavigation
import com.atharok.btremote.ui.views.remoteNavigation.RemoteSwipeNavigation
import org.koin.androidx.compose.koinViewModel

private enum class NavigationToggle {
    DIRECTION,
    MOUSE
}

@Composable
fun RemoteScreen(
    isBluetoothServiceRunning: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    closeApp: () -> Unit,
    navigateUp: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    remoteViewModel: RemoteViewModel = koinViewModel(),
    context: Context = LocalContext.current
) {
    val configuration = LocalConfiguration.current

    val remoteSettings by remoteViewModel
        .remoteSettingsFlow.collectAsStateWithLifecycle(RemoteSettings())

    // Remote
    var navigationToggle by rememberSaveable { mutableStateOf(NavigationToggle.DIRECTION) }

    // Keyboard
    var showKeyboard: Boolean by rememberSaveable { mutableStateOf(false) }

    // Help
    var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }

    BackHandler(enabled = true, onBack = closeApp)

    DisposableEffect(isBluetoothServiceRunning) {
        if(!isBluetoothServiceRunning) {
            navigateUp()
        }
        onDispose {}
    }

    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_DISCONNECTED) {
            navigateUp()
        }
        onDispose {}
    }

    StatelessRemoteScreen(
        deviceName = bluetoothDeviceHidConnectionState.deviceName,
        isLandscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
        topBarActions = {
            TopBarActions(
                navigateToSettings = navigateToSettings,
                disconnectDevice = {
                    remoteViewModel.disconnectDevice()
                },
                navigationToggle = navigationToggle,
                onNavigationToggleChanged = { navigationToggle = it },
                useAdvancedKeyboardIntegrated = remoteSettings.useAdvancedKeyboard && remoteSettings.useAdvancedKeyboardIntegrated,
                showKeyboard = showKeyboard,
                onShowKeyboardChanged = { showKeyboard = it },
                showHelpBottomSheet = showHelpBottomSheet,
                onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
                sendRemoteKeyReport = remoteViewModel.sendRemoteReport,
                showBrightnessButtons = !remoteSettings.useMinimalistRemote
            )
        },
        remoteLayout = {
            RemoteLayout(
                useMinimalistRemote = remoteSettings.useMinimalistRemote,
                showAdvancedKeyboard = remoteSettings.useAdvancedKeyboard && remoteSettings.useAdvancedKeyboardIntegrated && showKeyboard,
                keyboardLanguage = remoteSettings.keyboardLanguage,
                sendRemoteKeyReport = remoteViewModel.sendRemoteReport,
                sendKeyboardKeyReport = remoteViewModel.sendKeyboardReport
            )
        },
        navigationLayout = {
            NavigationLayout(
                remoteSettings = remoteSettings,
                sendRemoteKeyReport = remoteViewModel.sendRemoteReport,
                sendKeyboardKeyReport = remoteViewModel.sendKeyboardReport,
                sendMouseKeyReport = remoteViewModel.sendMouseReport,
                navigationToggle = navigationToggle,
            )
        },
        overlayView = {
            // Dialog / ModalBottomSheet
            when {
                bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_DISCONNECTING -> {
                    LoadingDialog(
                        title = stringResource(id = R.string.connection),
                        message = stringResource(id = R.string.bluetooth_device_disconnecting_message, bluetoothDeviceHidConnectionState.deviceName),
                        buttonText = stringResource(id = R.string.disconnect),
                        onButtonClick = {
                            remoteViewModel.disconnectDevice()
                            BluetoothHidService.stop(context) // force disconnection
                        }
                    )
                }
                showHelpBottomSheet -> {
                    RemoteScreenHelpModalBottomSheet(
                        onDismissRequest = { showHelpBottomSheet = false },
                        modifier = modifier
                    )
                }
                showKeyboard && (!remoteSettings.useAdvancedKeyboard || !remoteSettings.useAdvancedKeyboardIntegrated) -> {
                    KeyboardModalBottomSheet(
                        useAdvancedKeyboard = remoteSettings.useAdvancedKeyboard,
                        keyboardLanguage = remoteSettings.keyboardLanguage,
                        mustClearInputField = remoteSettings.mustClearInputField,
                        sendKeyboardKeyReport = remoteViewModel.sendKeyboardReport,
                        sendTextReport = remoteViewModel.sendTextReport,
                        onShowKeyboardChanged = { showKeyboard = it }
                    )
                }
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatelessRemoteScreen(
    deviceName: String,
    isLandscapeMode: Boolean,
    topBarActions: @Composable (RowScope.() -> Unit),
    remoteLayout: @Composable () -> Unit,
    navigationLayout: @Composable () -> Unit,
    overlayView: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = deviceName,
        modifier = modifier,
        scrollBehavior = null,
        topBarActions = topBarActions
    ) { innerPadding ->

        if(isLandscapeMode) {
            RemoteLandscapeView(
                remoteLayout = remoteLayout,
                navigationLayout = navigationLayout,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            RemotePortraitView(
                remoteLayout = remoteLayout,
                navigationLayout = navigationLayout,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }

        // Dialog / ModalBottomSheet
        overlayView()
    }
}

@Composable
private fun RemoteLandscapeView(
    remoteLayout: @Composable () -> Unit,
    navigationLayout: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Row(
        modifier = modifier.onGloballyPositioned { layoutCoordinates ->
            rowSize = layoutCoordinates.size.toSize() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = with(LocalDensity.current) { (0.5f * rowSize.width).toDp() })
                .align(Alignment.CenterVertically)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_large),
                    top = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_large)
                ),
        ) {
            navigationLayout()
        }

        Box(
            modifier = Modifier.align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            remoteLayout()
        }
    }
}

@Composable
private fun RemotePortraitView(
    remoteLayout: @Composable () -> Unit,
    navigationLayout: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .heightIn(
                    max = with(LocalConfiguration.current) {
                        if (screenHeightDp >= screenWidthDp * 1.9f) // Si la hauteur de l'appareil est suffisamment haute par rapport à sa largeur (ratio ~ 1/2)
                            screenWidthDp.dp // On peut se permettre de prendre pour hauteur la largeur de l'écran
                        else // Sinon
                            (screenHeightDp * 0.50).dp // On prend 50% de la hauteur de l'écran
                    }
                )
                .align(Alignment.CenterHorizontally),
        ) {
            remoteLayout()
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_large)
                ),
            contentAlignment = Alignment.Center
        ) {
            navigationLayout()
        }
    }
}

@Composable
private fun RemoteLayout(
    useMinimalistRemote: Boolean,
    showAdvancedKeyboard: Boolean,
    keyboardLanguage: KeyboardLanguage,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit
) {
    FadeAnimatedContent(targetState = showAdvancedKeyboard) {
        if (it) {
            AdvancedKeyboard(
                keyboardLanguage = keyboardLanguage,
                sendKeyboardKeyReport = sendKeyboardKeyReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_large)),
                keyElevation = surfaceElevationMedium()
            )
        } else {
            RemoteButtonsLayouts(
                useMinimalistRemote = useMinimalistRemote,
                multimediaPlayPauseTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_PLAY_PAUSE) },
                multimediaPreviousTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_PREVIOUS) },
                multimediaNextTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_NEXT) },
                volumeIncTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_VOLUME_INC) },
                volumeDecTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_VOLUME_DEC) },
                volumeMuteTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_VOLUME_MUTE) },
                closedCaptionsTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_CLOSED_CAPTIONS) },
                backTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_BACK) },
                homeTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_HOME) },
                menuTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU) },
                powerTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_POWER) },
                brightnessIncTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_BRIGHTNESS_INC) },
                brightnessDecTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_BRIGHTNESS_DEC) },
                tvChannelIncTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_CHANNEL_INC) },
                tvChannelDecTouchDown = { sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_CHANNEL_DEC) },
                tvChannel1TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_1) },
                tvChannel2TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_2) },
                tvChannel3TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_3) },
                tvChannel4TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_4) },
                tvChannel5TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_5) },
                tvChannel6TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_6) },
                tvChannel7TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_7) },
                tvChannel8TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_8) },
                tvChannel9TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_9) },
                tvChannel0TouchDown = { sendKeyboardKeyReport(ChannelInput.CHANNEL_INPUT_0) },
                remoteTouchUp = { sendRemoteKeyReport(REMOTE_INPUT_NONE) },
                keyboardTouchUp = { sendKeyboardKeyReport(REMOTE_INPUT_NONE) },
            )
        }
    }
}

@Composable
private fun RemoteButtonsLayouts(
    useMinimalistRemote: Boolean,
    multimediaPlayPauseTouchDown: () -> Unit,
    multimediaPreviousTouchDown: () -> Unit,
    multimediaNextTouchDown: () -> Unit,
    volumeIncTouchDown: () -> Unit,
    volumeDecTouchDown: () -> Unit,
    volumeMuteTouchDown: () -> Unit,
    closedCaptionsTouchDown: () -> Unit,
    backTouchDown: () -> Unit,
    homeTouchDown: () -> Unit,
    menuTouchDown: () -> Unit,
    powerTouchDown: () -> Unit,
    brightnessIncTouchDown: () -> Unit,
    brightnessDecTouchDown: () -> Unit,
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
) {
    if (useMinimalistRemote) {
        var showTVChannelButtons: Boolean by remember { mutableStateOf(false) }

        MinimalistRemoteView(
            multimediaPlayPauseTouchDown = multimediaPlayPauseTouchDown,
            multimediaPreviousTouchDown = multimediaPreviousTouchDown,
            multimediaNextTouchDown = multimediaNextTouchDown,
            volumeIncTouchDown = volumeIncTouchDown,
            volumeDecTouchDown = volumeDecTouchDown,
            volumeMuteTouchDown = volumeMuteTouchDown,
            closedCaptionsTouchDown = closedCaptionsTouchDown,
            backTouchDown = backTouchDown,
            homeTouchDown = homeTouchDown,
            menuTouchDown = menuTouchDown,
            powerTouchDown = powerTouchDown,
            brightnessIncTouchDown = brightnessIncTouchDown,
            brightnessDecTouchDown = brightnessDecTouchDown,
            remoteTouchUp = remoteTouchUp,
            showTVChannelButtons = {
                showTVChannelButtons = !showTVChannelButtons
            },
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal))
        )

        if (showTVChannelButtons) {
            TVChannelDialog(
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
                onDismissRequest = {
                    showTVChannelButtons = false
                }
            )
        }
    } else {
        RemoteView(
            multimediaPlayPauseTouchDown = multimediaPlayPauseTouchDown,
            multimediaPreviousTouchDown = multimediaPreviousTouchDown,
            multimediaNextTouchDown = multimediaNextTouchDown,
            volumeIncTouchDown = volumeIncTouchDown,
            volumeDecTouchDown = volumeDecTouchDown,
            volumeMuteTouchDown = volumeMuteTouchDown,
            closedCaptionsTouchDown = closedCaptionsTouchDown,
            backTouchDown = backTouchDown,
            homeTouchDown = homeTouchDown,
            menuTouchDown = menuTouchDown,
            powerTouchDown = powerTouchDown,
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
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal))
        )
    }
}

@Composable
private fun NavigationLayout(
    remoteSettings: RemoteSettings,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseAction, x: Float, y: Float, wheel: Float) -> Unit,
    navigationToggle: NavigationToggle
) {
    FadeAnimatedContent(targetState = navigationToggle) {
        when(it) {
            NavigationToggle.DIRECTION -> {
                RemotePadLayout(
                    remoteNavigationMode = remoteSettings.remoteNavigationEntity,
                    upTouchDown = {
                        sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU_UP)
                    },
                    downTouchDown = {
                        sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU_DOWN)
                    },
                    leftTouchDown = {
                        sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU_LEFT)
                    },
                    rightTouchDown = {
                        sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU_RIGHT)
                    },
                    pickTouchDown = {
                        if(remoteSettings.useEnterForSelection) {
                            sendKeyboardKeyReport(byteArrayOf(0x00, KeyboardKey.KEY_ENTER.byte))
                        } else {
                            sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_MENU_PICK)
                        }
                    },
                    directionTouchUp = {
                        sendRemoteKeyReport(REMOTE_INPUT_NONE)
                    },
                    pickTouchUp = {
                        if(remoteSettings.useEnterForSelection) {
                            sendKeyboardKeyReport(REMOTE_INPUT_NONE)
                        } else {
                            sendRemoteKeyReport(REMOTE_INPUT_NONE)
                        }
                    }
                )
            }

            NavigationToggle.MOUSE -> {
                MousePadLayout(
                    mouseSpeed = remoteSettings.mouseSpeed,
                    shouldInvertMouseScrollingDirection = remoteSettings.shouldInvertMouseScrollingDirection,
                    useGyroscope = remoteSettings.useGyroscope,
                    sendMouseInput = sendMouseKeyReport,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun RemotePadLayout(
    remoteNavigationMode: RemoteNavigationEntity,
    upTouchDown: () -> Unit,
    downTouchDown: () -> Unit,
    leftTouchDown: () -> Unit,
    rightTouchDown: () -> Unit,
    pickTouchDown: () -> Unit,
    directionTouchUp: () -> Unit,
    pickTouchUp: () -> Unit,
) {
    if(remoteNavigationMode == RemoteNavigationEntity.D_PAD) {
        RemoteDirectionalPadNavigation(
            upTouchDown = upTouchDown,
            downTouchDown = downTouchDown,
            leftTouchDown = leftTouchDown,
            rightTouchDown = rightTouchDown,
            pickTouchDown = pickTouchDown,
            directionTouchUp = directionTouchUp,
            pickTouchUp = pickTouchUp,
            modifier = Modifier.aspectRatio(1f)
        )
    } else {
        RemoteSwipeNavigation(
            upTouchDown = upTouchDown,
            downTouchDown = downTouchDown,
            leftTouchDown = leftTouchDown,
            rightTouchDown = rightTouchDown,
            pickTouchDown = pickTouchDown,
            directionTouchUp = directionTouchUp,
            pickTouchUp = pickTouchUp,
            modifier = Modifier
        )
    }
}

@Composable
private fun KeyboardModalBottomSheet(
    useAdvancedKeyboard: Boolean,
    keyboardLanguage: KeyboardLanguage,
    mustClearInputField: Boolean,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String, VirtualKeyboardLayout, Boolean) -> Unit,
    onShowKeyboardChanged: (Boolean) -> Unit,
) {
    if (useAdvancedKeyboard) {
        AdvancedKeyboardModalBottomSheet(
            keyboardLanguage = keyboardLanguage,
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            onShowKeyboardBottomSheetChanged = onShowKeyboardChanged
        )
    } else {
        var virtualKeyboardLayout: VirtualKeyboardLayout by remember {
            mutableStateOf(getKeyboardLayout(keyboardLanguage))
        }

        LaunchedEffect(keyboardLanguage) {
            virtualKeyboardLayout = getKeyboardLayout(keyboardLanguage)
        }

        VirtualKeyboardModalBottomSheet(
            mustClearInputField = mustClearInputField,
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            sendTextReport = { text: String, shouldSendEnter: Boolean ->
                sendTextReport(text, virtualKeyboardLayout, shouldSendEnter)
            },
            onShowKeyboardBottomSheetChanged = onShowKeyboardChanged
        )
    }
}

@Composable
private fun TopBarActions(
    navigateToSettings: () -> Unit,
    disconnectDevice: () -> Unit,
    navigationToggle: NavigationToggle,
    onNavigationToggleChanged: (NavigationToggle) -> Unit,
    useAdvancedKeyboardIntegrated: Boolean,
    showKeyboard: Boolean,
    onShowKeyboardChanged: (Boolean) -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    showBrightnessButtons: Boolean
) {
    FadeAnimatedContent(targetState = navigationToggle) {
        when (it) {
            NavigationToggle.MOUSE -> {
                DirectionButtonsAction(
                    showDirectionButtons = {
                        onNavigationToggleChanged(NavigationToggle.DIRECTION)
                    }
                )
            }

            NavigationToggle.DIRECTION -> {
                MouseAction(
                    showMousePad = {
                        onNavigationToggleChanged(NavigationToggle.MOUSE)
                    }
                )
            }
        }
    }

    if(useAdvancedKeyboardIntegrated) {
        FadeAnimatedContent(targetState = showKeyboard) {
            when (it) {
                true -> {
                    RemoteAction(
                        showRemote = {
                            onShowKeyboardChanged(false)
                        }
                    )
                }

                false -> {
                    KeyboardAction(
                        showKeyboard = {
                            onShowKeyboardChanged(true)
                        }
                    )
                }
            }
        }
    } else {
        KeyboardAction(
            showKeyboard = {
                onShowKeyboardChanged(!showKeyboard)
            }
        )
    }

    MoreOverflowMenu { closeDropdownMenu: () -> Unit ->
        if(showBrightnessButtons) {
            BrightnessIncDropdownMenuItem(
                touchDown = {
                    sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_BRIGHTNESS_INC)
                },
                touchUp = {
                    sendRemoteKeyReport(REMOTE_INPUT_NONE)
                }
            )
            BrightnessDecDropdownMenuItem(
                touchDown = {
                    sendRemoteKeyReport(RemoteInput.REMOTE_INPUT_BRIGHTNESS_DEC)
                },
                touchUp = {
                    sendRemoteKeyReport(REMOTE_INPUT_NONE)
                }
            )
        }
        DisconnectDropdownMenuItem(
            disconnect = {
                closeDropdownMenu()
                disconnectDevice()
            }
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
        HelpDropdownMenuItem(
            showHelp = {
                closeDropdownMenu()
                onShowHelpBottomSheetChanged(!showHelpBottomSheet)
            }
        )
        SettingsDropdownMenuItem(
            navigateToSettings = {
                closeDropdownMenu()
                navigateToSettings()
            }
        )
    }
}