package com.atharok.btremote.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.LocalActivity
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.SOURCE_CODE_LINK
import com.atharok.btremote.common.utils.WEB_SITE_LINK
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entity.RemoteNavigationEntity
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.FadeAnimatedContent
import com.atharok.btremote.ui.components.ListDialog
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextNormal
import com.atharok.btremote.ui.components.TextNormalSecondary

@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    openThirdLibrariesScreen: () -> Unit,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.settings),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {

            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current
            val horizontalPadding = dimensionResource(id = R.dimen.padding_max)
            val verticalPadding = dimensionResource(id = R.dimen.padding_large)

            // Appearance
            val theme: ThemeEntity by settingsViewModel.theme.collectAsStateWithLifecycle(initialValue = ThemeEntity.SYSTEM)
            val useBlackColorForDarkTheme: Boolean by settingsViewModel.useBlackColorForDarkTheme.collectAsStateWithLifecycle(initialValue = false)
            val useFullScreen: Boolean by settingsViewModel.useFullScreen.collectAsStateWithLifecycle(initialValue = false)
            // Remote
            val useMinimalistRemote: Boolean by settingsViewModel.useMinimalistRemote.collectAsStateWithLifecycle(initialValue = false)
            val remoteNavigation: RemoteNavigationEntity by settingsViewModel.remoteNavigation.collectAsStateWithLifecycle(initialValue = RemoteNavigationEntity.D_PAD)
            // Mouse
            val mouseSpeed by settingsViewModel.mouseSpeed.collectAsStateWithLifecycle(initialValue = MOUSE_SPEED_DEFAULT_VALUE)
            val shouldInvertMouseScrollingDirection: Boolean by settingsViewModel.shouldInvertMouseScrollingDirection.collectAsStateWithLifecycle(initialValue = false)
            val useGyroscope: Boolean by settingsViewModel.useGyroscope.collectAsStateWithLifecycle(initialValue = false)
            // Keyboard
            val keyboardLanguage: KeyboardLanguage by settingsViewModel.keyboardLanguage.collectAsStateWithLifecycle(initialValue = KeyboardLanguage.ENGLISH_US)
            val mustClearInputField: Boolean by settingsViewModel.mustClearInputField.collectAsStateWithLifecycle(initialValue = true)
            val useAdvancedKeyboard: Boolean by settingsViewModel.useAdvancedKeyboard.collectAsStateWithLifecycle(initialValue = false)
            val useAdvancedKeyboardIntegrated: Boolean by settingsViewModel.useAdvancedKeyboardIntegrated.collectAsStateWithLifecycle(initialValue = false)

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
                value = theme,
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
                checked = useBlackColorForDarkTheme,
                onCheckedChange = { settingsViewModel.setUseBlackColorForDarkTheme(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if(isDynamicColorsAvailable()) {
                val useDynamicColors: Boolean by settingsViewModel.useDynamicColors.collectAsStateWithLifecycle(initialValue = true)
                SettingsSwitch(
                    primaryText = stringResource(id = R.string.dynamic_colors),
                    secondaryText = null,
                    checked = useDynamicColors,
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
                checked = useFullScreen,
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
                checked = useMinimalistRemote,
                onCheckedChange = { settingsViewModel.saveUseMinimalistRemote(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            SettingsRemoteNavigationSelector(
                remoteNavigation = remoteNavigation,
                onRemoteNavigationChange = { settingsViewModel.saveRemoteNavigation(it) },
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
                value = mouseSpeed,
                onValueChange = { settingsViewModel.saveMouseSpeed(it) },
                info = stringResource(id = R.string.mouse_pointer_speed) + " (x$mouseSpeed)",
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
                checked = shouldInvertMouseScrollingDirection,
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
                checked = useGyroscope,
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
                value = keyboardLanguage,
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
                checked = useAdvancedKeyboard,
                onCheckedChange = { settingsViewModel.saveUseAdvancedKeyboard(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            FadeAnimatedContent(
                targetState = useAdvancedKeyboard
            ) {
                if(it) {
                    SettingsSwitch(
                        primaryText = stringResource(id = R.string.integrate_advanced_keyboard_into_the_view),
                        secondaryText = null,
                        checked = useAdvancedKeyboardIntegrated,
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
                        checked = mustClearInputField,
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
                        openThirdLibrariesScreen()
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
        modifier = modifier
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
            .then(modifier)
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
            modifier = Modifier.fillMaxWidth(0.8f)
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
    value: Float,
    onValueChange: (Float) -> Unit,
    info: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TextNormal(
            text = info,
            modifier = Modifier.fillMaxWidth().padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 1f..5f,
            steps = 15,
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