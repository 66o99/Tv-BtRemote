package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.ui.theme.dimensionElevation4

@Composable
fun VirtualKeyboardModalBottomSheet(
    mustClearInputField: Boolean,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardModalBottomSheet(
        onShowKeyboardBottomSheetChanged = onShowKeyboardBottomSheetChanged,
        windowInsets = WindowInsets.ime,
        modifier = modifier
    ) {
        val focusRequester = remember { FocusRequester() }
        val textState = remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        StatelessKeyboardView(
            mustClearInputField = mustClearInputField,
            focusRequester = focusRequester,
            text = textState.value,
            onTextChange = {
                textState.value = it
            },
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            sendTextReport = sendTextReport,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .padding(bottom = dimensionResource(id = R.dimen.padding_max))
        )
    }
}

@Composable
private fun StatelessKeyboardView(
    mustClearInputField: Boolean,
    focusRequester: FocusRequester,
    text: String,
    onTextChange: (String) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(4f)
                    .padding(end = dimensionResource(id = R.dimen.padding_min))
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        sendTextReport(text)
                        sendKeyboardKeyReport(VirtualKeyboardLayout.KEYBOARD_KEY_ENTER)
                        sendKeyboardKeyReport(REMOTE_INPUT_NONE)
                        if(mustClearInputField) {
                            onTextChange("")
                        }
                    }
                )
            )
            IconButton(
                onClick = {
                    sendTextReport(text)
                    if(mustClearInputField) {
                        onTextChange("")
                    }
                },
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_min))
            ) {
                Icon(
                    imageVector = AppIcons.Send,
                    contentDescription = stringResource(id = R.string.send)
                )
            }
        }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            AdditionalKeyboardKeysLayout(
                sendKeyboardKeyReport = sendKeyboardKeyReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(unbounded = true)
                    .padding(top = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun AdditionalKeyboardKeysLayout(
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min))
        ) {
            VirtualKeyboardKey(
                image = AppIcons.SpaceBar,
                contentDescription = stringResource(id = R.string.space_bar),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_SPACE_BAR,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(3f).padding(end = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardArrowUp,
                contentDescription = stringResource(id = R.string.up),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_UP,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardScreenshot,
                contentDescription = stringResource(id = R.string.print_screen),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_PRINT_SCREEN,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_min))
            )
        }

        Row(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min))
        ) {
            VirtualKeyboardKey(
                image = AppIcons.KeyboardBackspace,
                contentDescription = stringResource(id = R.string.delete),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_DELETE,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(end = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardEnter,
                contentDescription = stringResource(id = R.string.enter),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_ENTER,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.left),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_LEFT,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.down),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_DOWN,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_min))
            )

            VirtualKeyboardKey(
                image = AppIcons.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.right),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_RIGHT,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_min))
            )
        }
    }
}

@Composable
private fun VirtualKeyboardKey(
    image: ImageVector,
    contentDescription: String,
    bytes: ByteArray,
    sendKeyboardKey: (ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius)),
    elevation: Dp = dimensionElevation4()
) {
    KeyboardKeyView(
        touchDown = { sendKeyboardKey(bytes) },
        touchUp = { sendKeyboardKey(REMOTE_INPUT_NONE) },
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}