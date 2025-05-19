package com.atharok.btremote.ui.views

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.ui.components.TemplateModalBottomSheet
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormalSecondary

@Composable
private fun HelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    TemplateModalBottomSheet(
        title = stringResource(id = R.string.help),
        icon = AppIcons.Help,
        iconDescription = stringResource(id = R.string.help),
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = content
    )
}

@Composable
fun DevicesSelectionScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        // Instruction
        Section(
            title = stringResource(id = R.string.connection),
            message = stringResource(id = R.string.help_select_device_from_list),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
        )

        // Missing device
        Section(
            title = stringResource(id = R.string.help_missing_device_title),
            message = stringResource(id = R.string.help_missing_device_message)
        )

        // Connection failure
        Section(
            title = stringResource(id = R.string.help_device_failed_connection_title),
            message = buildString {
                append(stringResource(id = R.string.help_device_failed_connection_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_1))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_2))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_3))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_4))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_message_2))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_5))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_6))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_message_3))
            }
        )

        // HID Service Not Initialized
        Section(
            title = stringResource(id = R.string.help_hid_service_not_initialized_title),
            message = buildString {
                append(stringResource(id = R.string.help_hid_service_not_initialized_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_hid_service_not_initialized_message_2))
                append("\n")
                append(stringResource(id = R.string.help_hid_service_not_initialized_message_3))
            }
        )

        // Remote control buttons
        Section(
            title = stringResource(id = R.string.help_remote_control_buttons_are_not_working_title),
            message = buildString {
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_message))
                append("\n\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_1))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_2))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_3))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_6))
                append("\n\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_message_2))
            },
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
        )

        // Keyboard
        Section(
            title = stringResource(id = R.string.keyboard),
            message = stringResource(id = R.string.help_keyboard_wrong_character_sent_message)
        )
    }
}

@Composable
fun BluetoothScanningScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        // Instruction
        Section(
            title = stringResource(id = R.string.pairing_a_device),
            message = stringResource(id = R.string.help_paring_select_device_from_list),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
        )

        // Missing device
        Section(
            title = stringResource(id = R.string.help_missing_device_title),
            message = buildString {
                append(stringResource(id = R.string.help_pairing_missing_device_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_1))
                append("\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_2))
                append("\n")
                append(stringResource(id = R.string.help_pairing_missing_device_check_3))
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                    append("\n")
                    append(stringResource(id = R.string.help_pairing_missing_device_check_location_for_android_11_or_less))
                }
            }
        )

        // Connection failure
        Section(
            title = stringResource(id = R.string.help_device_failed_connection_title),
            message = buildString {
                append(stringResource(id = R.string.help_pairing_device_failed_connection_message_1))
                append("\n\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_1))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_2))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_3))
                append("\n")
                append(stringResource(id = R.string.help_device_failed_connection_check_4))
            }
        )
    }
}

@Composable
fun RemoteScreenHelpModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HelpModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        // Remote control buttons
        Section(
            title = stringResource(id = R.string.help_remote_control_buttons_are_not_working_title),
            message = buildString {
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_message))
                append("\n\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_1))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_2))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_3))
                append("\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_check_4))
                append("\n\n")
                append(stringResource(id = R.string.help_remote_control_buttons_are_not_working_message_2))
            },
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
        )

        // Keyboard
        Section(
            title = stringResource(id = R.string.keyboard),
            message = stringResource(id = R.string.help_keyboard_wrong_character_sent_message)
        )
    }
}

@Composable
private fun Section(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextMedium(
            text = title
        )
        TextNormalSecondary(
            text = message,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_max))
        )
    }
}