package com.atharok.btremote.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.theme.dimensionElevation1

@Composable
fun TemplateDialog(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    confirmButtonText: String? = null,
    onConfirmation: () -> Unit = {},
    dismissButtonText: String? = null,
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        title = title,
        text = content,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            DialogButton(text = confirmButtonText, action = onConfirmation)
        },
        dismissButton = {
            DialogButton(text = dismissButtonText, action = onDismissRequest)
        },
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(dimensionElevation1()),
        modifier = modifier
    )
}

@Composable
fun SimpleDialog(
    confirmButtonText: String?,
    dismissButtonText: String?,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    modifier: Modifier = Modifier
) {
    TemplateDialog(
        title = {
            TextLarge(text = dialogTitle)
        },
        content = {
            TextNormal(text = dialogText)
        },
        modifier = modifier,
        confirmButtonText = confirmButtonText,
        onConfirmation = onConfirmation,
        dismissButtonText = dismissButtonText,
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun ListDialog(
    confirmButtonText: String?,
    dismissButtonText: String?,
    onConfirmation: (itemSelected: Int) -> Unit,
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dialogMessage: String? = null,
    items: List<String>,
    defaultItemIndex: Int = 0
) {
    val selected = remember { mutableIntStateOf(defaultItemIndex) }
    TemplateDialog(
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextLarge(text = dialogTitle)
                dialogMessage?.let {
                    TextNormalSecondary(
                        text = it,
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_max))
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                items.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selected.intValue == index,
                            onClick = { selected.intValue = index }
                        )
                        TextNormal(
                            text = item,
                            modifier = Modifier.clickable { selected.intValue = index }
                        )
                    }
                }
            }
        },
        confirmButtonText = confirmButtonText,
        onConfirmation = {
            onConfirmation(selected.intValue)
        },
        dismissButtonText = dismissButtonText,
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun LoadingDialog(
    title: String,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    TemplateDialog(
        title = {
            TextLarge(text = title)
        },
        content = {
            LoadingView(
                message = message,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_max))
            )
        },
        confirmButtonText = buttonText,
        onConfirmation = onButtonClick,
        onDismissRequest = onButtonClick
    )
}

@Composable
private fun DialogButton(
    text: String?,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    text?.let {
        TextButton(
            onClick = action,
            modifier = modifier
        ) {
            TextNormal(text = text)
        }
    }
}

@Composable
private fun LoadingView(
    message: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_max))
    ) {
        CircularProgressIndicator()
        TextNormal(text = message)
    }
}