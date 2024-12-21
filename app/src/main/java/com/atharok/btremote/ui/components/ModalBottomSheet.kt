package com.atharok.btremote.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons.getIconModifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateModalBottomSheet(
    title: String,
    icon: ImageVector,
    iconDescription: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(dimensionResource(id = R.dimen.elevation_1)),
        contentWindowInsets = {
            WindowInsets(0, 0, 0, 0)
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = dimensionResource(id = R.dimen.padding_max),
                    end = dimensionResource(id = R.dimen.padding_max),
                    bottom = dimensionResource(id = R.dimen.padding_max)
                )
        ) {
            Row(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_max)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    modifier = getIconModifier(icon).size(dimensionResource(id = R.dimen.large_icon_size)),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                )
                TextLarge(
                    text = title,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
                )
            }
            content()
        }
    }
}