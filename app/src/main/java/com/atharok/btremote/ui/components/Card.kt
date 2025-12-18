package com.atharok.btremote.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R
import com.atharok.btremote.ui.theme.dimensionElevation2

@Composable
fun DefaultElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
    elevation: Dp = dimensionElevation2(),
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
        )
    ) {
        content()
    }
}