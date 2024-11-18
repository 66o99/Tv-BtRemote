package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.common.utils.getKeyboardLayout
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.remoteInput.MouseAction
import com.atharok.btremote.domain.entity.remoteInput.RemoteInput
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
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
import com.atharok.btremote.ui.components.SettingsDropdownMenuItem
import com.atharok.btremote.ui.views.RemoteScreenHelpModalBottomSheet
import com.atharok.btremote.ui.views.keyboard.AdvancedKeyboardLayoutView
import com.atharok.btremote.ui.views.keyboard.VirtualKeyboardView
import com.atharok.btremote.ui.views.mouse.MousePadLayout
import com.atharok.btremote.ui.views.remote.MinimalistRemoteView
import com.atharok.btremote.ui.views.remote.RemoteView
import com.atharok.btremote.ui.views.remoteButtons.DirectionalButtons

private enum class NavigationToggle {
    DIRECTION,
    MOUSE
}

@Composable
fun RemoteScreen(
    deviceName: String,
    isBluetoothServiceStarted: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    closeApp: () -> Unit,
    openSettings: () -> Unit,
    settingsViewModel: SettingsViewModel,
    disconnectDevice: () -> Unit,
    forceDisconnectDevice: () -> Unit,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    sendMouseKeyReport: (MouseAction, Float, Float, Float) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String, VirtualKeyboardLayout) -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulRemoteScreen(
        isBluetoothServiceStarted = isBluetoothServiceStarted,
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        navigateUp = navigateUp,
        closeApp = closeApp
    ) {

        var navigationToggle by rememberSaveable {
            mutableStateOf(NavigationToggle.DIRECTION)
        }

        var showKeyboard: Boolean by remember { mutableStateOf(false) }

        var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }

        val useMinimalistRemote: Boolean by settingsViewModel.useMinimalistRemote
            .collectAsStateWithLifecycle(initialValue = false)

        StatelessRemoteScreen(
            deviceName = deviceName,
            topBarActions = {
                TopBarActions(
                    openSettings = openSettings,
                    disconnectDevice = disconnectDevice,
                    navigationToggle = navigationToggle,
                    onNavigationToggleChanged = { navigationToggle = it },
                    showKeyboard = showKeyboard,
                    onShowKeyboardChanged = { showKeyboard = it },
                    showHelpBottomSheet = showHelpBottomSheet,
                    onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
                    sendRemoteKeyReport = sendRemoteKeyReport,
                    showBrightnessButtons = !useMinimalistRemote
                )
            },
            remoteLayout = {
                if(useMinimalistRemote) {
                    MinimalistRemoteView(
                        sendRemoteKeyReport = sendRemoteKeyReport,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.remote_button_padding))
                    )
                } else {
                    RemoteView(
                        sendRemoteKeyReport = sendRemoteKeyReport,
                        sendNumberKeyReport = sendKeyboardKeyReport,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.remote_button_padding))
                    )
                }
            },
            navigationLayout = {
                NavigationLayout(
                    settingsViewModel = settingsViewModel,
                    sendRemoteKeyReport = sendRemoteKeyReport,
                    sendMouseKeyReport = sendMouseKeyReport,
                    navigationToggle = navigationToggle
                )
            },
            keyboardView = {
                KeyboardModalSheet(
                    settingsViewModel = settingsViewModel,
                    sendKeyboardKeyReport = sendKeyboardKeyReport,
                    sendTextReport = sendTextReport,
                    showKeyboard = showKeyboard,
                    onShowKeyboardChanged = { showKeyboard = it }
                )
            },
            helpModalBottomSheet = {
                HelpBottomSheet(
                    showHelpBottomSheet = showHelpBottomSheet,
                    onShowHelpBottomSheetChanged = { showHelpBottomSheet = it }
                )
            },
            modifier = modifier
        )

        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_DISCONNECTING) {
            LoadingDialog(
                title = stringResource(id = R.string.connection),
                message = stringResource(id = R.string.bluetooth_device_disconnecting_message, bluetoothDeviceHidConnectionState.deviceName),
                buttonText = stringResource(id = R.string.disconnect),
                onButtonClick = forceDisconnectDevice
            )
        }
    }
}

@Composable
private fun StatefulRemoteScreen(
    isBluetoothServiceStarted: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    closeApp: () -> Unit,
    content: @Composable () -> Unit
) {

    BackHandler(enabled = true, onBack = closeApp)

    DisposableEffect(isBluetoothServiceStarted) {
        if(!isBluetoothServiceStarted) {
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

    content()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatelessRemoteScreen(
    deviceName: String,
    topBarActions: @Composable (RowScope.() -> Unit),
    remoteLayout: @Composable () -> Unit,
    navigationLayout: @Composable () -> Unit,
    keyboardView: @Composable () -> Unit,
    helpModalBottomSheet: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = deviceName,
        modifier = modifier,
        scrollBehavior = null,
        topBarActions = topBarActions
    ) { innerPadding ->

        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if(isLandscape) {
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

        // ---- ModalBottomSheet ----

        keyboardView()

        helpModalBottomSheet()
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
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates -> rowSize = layoutCoordinates.size.toSize() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = with(LocalDensity.current) { (0.5f * rowSize.width).toDp() })
                .align(Alignment.CenterVertically)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                    top = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                ),
        ) {
            navigationLayout()
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically),
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
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                ),
            contentAlignment = Alignment.Center
        ) {
            navigationLayout()
        }
    }
}

@Composable
private fun NavigationLayout(
    settingsViewModel: SettingsViewModel,
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseAction, x: Float, y: Float, wheel: Float) -> Unit,
    navigationToggle: NavigationToggle
) {
    FadeAnimatedContent(targetState = navigationToggle) {
        when(it) {
            NavigationToggle.DIRECTION -> {
                DirectionalButtons(
                    sendRemoteKeyReport = sendRemoteKeyReport,
                    modifier = Modifier.aspectRatio(1f)
                )
            }

            NavigationToggle.MOUSE -> {

                val mouseSpeed: Float by settingsViewModel.mouseSpeed
                    .collectAsStateWithLifecycle(initialValue = MOUSE_SPEED_DEFAULT_VALUE)

                val shouldInvertMouseScrollingDirection: Boolean by settingsViewModel.shouldInvertMouseScrollingDirection
                    .collectAsStateWithLifecycle(initialValue = false)

                val useGyroscope: Boolean by settingsViewModel.useGyroscope.collectAsStateWithLifecycle(initialValue = false)

                MousePadLayout(
                    mouseSpeed = mouseSpeed,
                    shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
                    useGyroscope = useGyroscope,
                    sendMouseInput = sendMouseKeyReport,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun KeyboardModalSheet(
    settingsViewModel: SettingsViewModel,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String, VirtualKeyboardLayout) -> Unit,
    showKeyboard: Boolean,
    onShowKeyboardChanged: (Boolean) -> Unit,
) {
    val useAdvancedKeyboard: Boolean by settingsViewModel.useAdvancedKeyboard
        .collectAsStateWithLifecycle(initialValue = false)

    val keyboardLanguage: KeyboardLanguage by settingsViewModel.keyboardLanguage
        .collectAsStateWithLifecycle(initialValue = KeyboardLanguage.ENGLISH_US)

    if (useAdvancedKeyboard) {
        AdvancedKeyboardLayoutView(
            keyboardLanguage = keyboardLanguage,
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            showKeyboardLayoutBottomSheet = showKeyboard,
            onShowKeyboardLayoutBottomSheetChanged = onShowKeyboardChanged
        )
    } else {

        val mustClearInputField: Boolean by settingsViewModel.mustClearInputField
            .collectAsStateWithLifecycle(initialValue = false)

        var virtualKeyboardLayout: VirtualKeyboardLayout by remember {
            mutableStateOf(getKeyboardLayout(keyboardLanguage))
        }

        LaunchedEffect(keyboardLanguage) {
            virtualKeyboardLayout = getKeyboardLayout(keyboardLanguage)
        }

        VirtualKeyboardView(
            mustClearInputField = mustClearInputField,
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            sendTextReport = { sendTextReport(it, virtualKeyboardLayout) },
            showKeyboardBottomSheet = showKeyboard,
            onShowKeyboardBottomSheetChanged = onShowKeyboardChanged
        )
    }
}

@Composable
private fun HelpBottomSheet(
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if(showHelpBottomSheet) {
        RemoteScreenHelpModalBottomSheet(
            onDismissRequest = { onShowHelpBottomSheetChanged(false) },
            modifier = modifier
        )
    }
}

@Composable
private fun TopBarActions(
    openSettings: () -> Unit,
    disconnectDevice: () -> Unit,
    navigationToggle: NavigationToggle,
    onNavigationToggleChanged: (NavigationToggle) -> Unit,
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

    KeyboardAction(
        showKeyboard = {
            onShowKeyboardChanged(!showKeyboard)
        }
    )

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
                .padding(dimensionResource(id = R.dimen.padding_standard))
        )
        HelpDropdownMenuItem(
            showHelp = {
                closeDropdownMenu()
                onShowHelpBottomSheetChanged(!showHelpBottomSheet)
            }
        )
        SettingsDropdownMenuItem(
            showSettingsScreen = {
                closeDropdownMenu()
                openSettings()
            }
        )
    }
}