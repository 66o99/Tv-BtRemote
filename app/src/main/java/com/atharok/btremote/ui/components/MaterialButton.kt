package com.atharok.btremote.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R

@Composable
fun MaterialButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        ButtonContent(
            text = text,
            icon = icon
        )
    }
}

@Composable
fun MaterialFilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    icon: ImageVector? = null
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier
    ) {
        ButtonContent(
            text = text,
            icon = icon
        )
    }
}

@Composable
fun MaterialOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    icon: ImageVector? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        ButtonContent(
            text = text,
            icon = icon,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            iconColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun ButtonContent(
    text: String = "",
    icon: ImageVector? = null,
    textColor: Color = Color.Unspecified,
    iconColor: Color = LocalContentColor.current
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_standard))
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,// Le texte sert de description
                modifier = Modifier.padding(0.dp),
                tint = iconColor
            )
        }

        TextNormal(
            text = text,
            color = textColor
        )
    }
}