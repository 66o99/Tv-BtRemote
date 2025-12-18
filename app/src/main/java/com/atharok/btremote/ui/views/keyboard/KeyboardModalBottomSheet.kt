package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.atharok.btremote.ui.theme.dimensionElevation2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardModalBottomSheet(
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    windowInsets: WindowInsets,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        onDismissRequest = { onShowKeyboardBottomSheetChanged(false) },
        modifier = modifier,
        sheetState = rememberModalBottomSheetState(true),
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(dimensionElevation2()),
        contentWindowInsets = {
            windowInsets
        }
    ) {
        content()
    }
}