package com.atharok.btremote.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.LocalActivity
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.SOURCE_CODE_LINK
import com.atharok.btremote.common.utils.WEB_SITE_LINK
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entities.RemoteNavigationEntity
import com.atharok.btremote.domain.entities.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entities.settings.AppearanceSettings
import com.atharok.btremote.domain.entities.settings.RemoteSettings
import com.atharok.btremote.domain.entities.settings.ThemeEntity
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.FadeAnimatedContent
import com.atharok.btremote.ui.components.ListDialog
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextNormal
import com.atharok.btremote.ui.components.TextNormalSecondary
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    navigateToThirdLibrariesScreen: () -> Unit,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel(),
    context: Context = LocalContext.current,
    uriHandler: UriHandler = LocalUriHandler.current
) {
    val appearanceSettings by settingsViewModel
        .appearanceSettingsFlow.collectAsStateWithLifecycle(AppearanceSettings())

    val remoteSettings by settingsViewModel
        .remoteSettingsFlow.collectAsStateWithLifecycle(RemoteSettings())

    // Advanced Options
    val hideBluetoothActivationButton: Boolean by settingsViewModel.hideBluetoothActivationButtonFlow.collectAsStateWithLifecycle(initialValue = false)

    AppScaffold(
        title = stringResource(id = R.string.settings),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        }
    ) { innerPadding ->

        val horizontalPadding = dimensionResource(id = R.dimen.padding_max)
        val verticalPadding = dimensionResource(id = R.dimen.padding_large)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // ---- Appearance ----

            SettingsTitle(
                text = stringResource(id = R.string.appearance),
                icon = AppIcons.Appearance,
                iconDescription = stringResource(id = R.string.appearance),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsListDialog(
                title = R.string.theme,
                dialogMessage = null,
                value = appearanceSettings.theme,
                onValueChange = { settingsViewModel.changeTheme(it) },
                items = ThemeEntity.entries,
                convertValueToString = { context.getString(it.stringRes) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.theme_black),
                secondaryText = stringResource(id = R.string.theme_black_oled_info),
                checked = appearanceSettings.useBlackColorForDarkTheme,
                onCheckedChange = { settingsViewModel.setUseBlackColorForDarkTheme(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if(isDynamicColorsAvailable()) {
                SettingsSwitch(
                    primaryText = stringResource(id = R.string.dynamic_colors),
                    secondaryText = null,
                    checked = appearanceSettings.useDynamicColors,
                    onCheckedChange = { settingsViewModel.setUseDynamicColors(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            SettingsSwitch(
                primaryText = stringResource(id = R.string.full_screen),
                secondaryText = null,
                checked = appearanceSettings.useFullScreen,
                onCheckedChange = { settingsViewModel.saveUseFullScreen(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Remote ----

            SettingsTitle(
                text = stringResource(id = R.string.remote),
                icon = AppIcons.RemoteControl,
                iconDescription = stringResource(id = R.string.remote),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.use_minimalist_interface),
                secondaryText = stringResource(id = R.string.minimalist_interface_info),
                checked = remoteSettings.useMinimalistRemote,
                onCheckedChange = { settingsViewModel.saveUseMinimalistRemote(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsRemoteNavigationSelector(
                remoteNavigation = remoteSettings.remoteNavigationEntity,
                onRemoteNavigationChange = { settingsViewModel.saveRemoteNavigation(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.use_enter_for_selection_title),
                secondaryText = stringResource(id = R.string.use_enter_for_selection_summary),
                checked = remoteSettings.useEnterForSelection,
                onCheckedChange = { settingsViewModel.saveUseEnterForSelection(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Mouse ----

            SettingsTitle(
                text = stringResource(id = R.string.mouse),
                icon = AppIcons.Mouse,
                iconDescription = stringResource(id = R.string.mouse),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSlider(
                valueRange = 0.1f..10f,
                steps = 197,
                value = remoteSettings.mouseSpeed,
                onValueChange = { settingsViewModel.saveMouseSpeed(it) },
                info = stringResource(id = R.string.mouse_pointer_speed) +
                        " (x${"%.2f".format(Locale.ROOT, remoteSettings.mouseSpeed)})",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSlider(
                valueRange = 0.1f..10f,
                steps = 197,
                value = remoteSettings.scrollSpeed,
                onValueChange = { settingsViewModel.saveScrollSpeed(it) },
                info = stringResource(id = R.string.scroll_speed) +
                        " (x${"%.2f".format(Locale.ROOT, remoteSettings.scrollSpeed)})",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )
            SettingsSwitch(
                primaryText = stringResource(id = R.string.invert_mouse_scrolling_direction),
                secondaryText = null,
                checked = remoteSettings.shouldInvertMouseScrollingDirection,
                onCheckedChange = { settingsViewModel.saveInvertMouseScrollingDirection(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.use_the_gyroscope_to_control_the_mouse),
                secondaryText = null,
                checked = remoteSettings.useGyroscope,
                onCheckedChange = { settingsViewModel.saveUseGyroscope(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Keyboard and Input Field ----

            SettingsTitle(
                text = stringResource(id = R.string.keyboard_and_input_field),
                icon = AppIcons.Keyboard,
                iconDescription = stringResource(id = R.string.keyboard),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsListDialog(
                title = R.string.keyboard_language,
                dialogMessage = stringResource(id = R.string.keyboard_language_info),
                value = remoteSettings.keyboardLanguage,
                onValueChange = { settingsViewModel.changeKeyboardLanguage(it) },
                items = KeyboardLanguage.entries.sortedBy { context.getString(it.language) },
                convertValueToString = { context.getString(it.language) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.advanced_keyboard),
                secondaryText = null,
                checked = remoteSettings.useAdvancedKeyboard,
                onCheckedChange = { settingsViewModel.saveUseAdvancedKeyboard(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            FadeAnimatedContent(
                targetState = remoteSettings.useAdvancedKeyboard
            ) {
                if(it) {
                    SettingsSwitch(
                        primaryText = stringResource(id = R.string.integrate_advanced_keyboard_into_the_view),
                        secondaryText = null,
                        checked = remoteSettings.useAdvancedKeyboardIntegrated,
                        onCheckedChange = { value -> settingsViewModel.saveUseAdvancedKeyboardIntegrated(value) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = horizontalPadding,
                                vertical = verticalPadding
                            )
                    )
                } else {
                    SettingsSwitch(
                        primaryText = stringResource(id = R.string.clear_input_field),
                        secondaryText = null,
                        checked = remoteSettings.mustClearInputField,
                        onCheckedChange = { value -> settingsViewModel.saveMustClearInputField(value) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = horizontalPadding,
                                vertical = verticalPadding
                            )
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Advanced Options ----

            SettingsTitle(
                text = stringResource(id = R.string.advanced_options),
                icon = AppIcons.Settings,
                iconDescription = stringResource(id = R.string.advanced_options),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsSwitch(
                primaryText = stringResource(id = R.string.hide_bluetooth_activation_button),
                secondaryText = null,
                checked = hideBluetoothActivationButton,
                onCheckedChange = { settingsViewModel.saveHideBluetoothActivationButton(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- About ----

            SettingsTitle(
                text = stringResource(id = R.string.about),
                icon = AppIcons.Info,
                iconDescription = stringResource(id = R.string.about),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val activity = LocalActivity.current
                SettingsText(
                    text = stringResource(id = R.string.language),
                    modifier = Modifier
                        .clickable {
                            activity?.startActivity(
                                Intent(
                                    Settings.ACTION_APP_LOCALE_SETTINGS,
                                    Uri.fromParts("package", activity.packageName, null)
                                )
                            )
                        }
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            SettingsText(
                text = stringResource(id = R.string.third_party_library),
                modifier = Modifier
                    .clickable {
                        navigateToThirdLibrariesScreen()
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsText(
                text = stringResource(id = R.string.website),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(WEB_SITE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsText(
                text = stringResource(id = R.string.source_code),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(SOURCE_CODE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )
        }
    }
}



@Composable
private fun SettingsRemoteNavigationSelector(
    remoteNavigation: RemoteNavigationEntity,
    onRemoteNavigationChange: (RemoteNavigationEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_min))
    ) {
        TextNormal(
            text = stringResource(id = R.string.navigation_mode),
            modifier = Modifier.fillMaxSize()
        )

        TextNormalSecondary(
            text = stringResource(id = remoteNavigation.description),
            modifier = Modifier.fillMaxSize().padding(bottom = dimensionResource(R.dimen.padding_small))
        )

        MultiChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            SegmentedButton(
                checked = remoteNavigation == RemoteNavigationEntity.D_PAD,
                onCheckedChange = { onRemoteNavigationChange(RemoteNavigationEntity.D_PAD) },
                shape = RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 0,
                    bottomEndPercent = 0,
                    bottomStartPercent = 50,
                )
            ) {
                TextNormal(text = stringResource(id = R.string.d_pad))
            }

            SegmentedButton(
                checked = remoteNavigation == RemoteNavigationEntity.TOUCHPAD,
                onCheckedChange = { onRemoteNavigationChange(RemoteNavigationEntity.TOUCHPAD) },
                shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 0,
                )
            ) {
                TextNormal(text = stringResource(id = R.string.touchpad))
            }
        }
    }

}

// --- Reusable components ----

@Composable
private fun <T> SettingsListDialog(
    @StringRes title: Int,
    dialogMessage: String?,
    value: T,
    onValueChange: (T) -> Unit,
    items: List<T>,
    convertValueToString: (T) -> String,
    modifier: Modifier = Modifier
) {
    var isShowingDialog by remember { mutableStateOf(false) }

    StatelessSettingsListDialog(
        value = value,
        onValueChange = onValueChange,
        items = items,
        convertValueToString = convertValueToString,
        showDialog = isShowingDialog,
        onShowDialogChange = { isShowingDialog = it },
        title = title,
        dialogMessage = dialogMessage,
        modifier = modifier
    )
}

@Composable
private fun <T> StatelessSettingsListDialog(
    value: T,
    onValueChange: (T) -> Unit,
    items: List<T>,
    convertValueToString: (T) -> String,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    @StringRes title: Int,
    dialogMessage: String?,
    modifier: Modifier = Modifier
) {
    if(showDialog) {
        ListDialog(
            confirmButtonText = stringResource(android.R.string.ok),
            dismissButtonText = stringResource(android.R.string.cancel),
            onConfirmation = { index ->
                onValueChange(items[index])
                onShowDialogChange(false)
            },
            onDismissRequest = { onShowDialogChange(false) },
            dialogTitle = stringResource(title),
            dialogMessage = dialogMessage,
            items = items.map { convertValueToString(it) },
            defaultItemIndex = items.indexOf(value)
        )
    }

    Column(
        modifier = Modifier
            .clickable { onShowDialogChange(true) }
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_min))
    ) {
        TextNormal(text = stringResource(id = title))
        TextNormalSecondary(text = convertValueToString(value))
    }
}

@Composable
private fun SettingsSwitch(
    primaryText: String,
    secondaryText: String?,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable { onCheckedChange(!checked) }
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_min))
        ) {
            TextNormal(text = primaryText)
            secondaryText?.let {
                TextNormalSecondary(text = it)
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = null//onCheckedChange
        )
    }
}

@Composable
private fun SettingsSlider(
    valueRange: ClosedFloatingPointRange<Float>,
    @IntRange(from = 0) steps: Int,
    value: Float,
    onValueChange: (Float) -> Unit,
    info: String,
    modifier: Modifier = Modifier,
    details: String? = null
) {
    Column(
        modifier = modifier
    ) {
        TextNormal(
            text = info,
            modifier = Modifier.fillMaxWidth().padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        details?.let {
            TextNormalSecondary(text = it)
        }
        Slider(
            valueRange = valueRange,
            steps = steps,
            value = value,
            onValueChange = onValueChange,
        )
    }
}

@Composable
private fun SettingsTitle(
    text: String,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(dimensionResource(id = R.dimen.medium_icon_size)),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
        )
        TextNormal(
            text = text,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun SettingsText(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        TextNormal(
            text = text,
            modifier = Modifier.fillMaxSize()
        )
    }
}