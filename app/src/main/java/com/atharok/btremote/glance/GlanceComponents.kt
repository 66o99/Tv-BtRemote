package com.atharok.btremote.glance

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.size
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

@Composable
fun GlanceWidgetTitle(
    appName: String,
    connectionState: String,
    dimensions: MediaControlWidgetDimensions,
    modifier: GlanceModifier = GlanceModifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = appName,
            style = TextStyle(
                color = GlanceTheme.colors.onSurfaceVariant,
                fontSize = dimensions.minTextSp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start
            ),
            maxLines = 1
        )
        Spacer(GlanceModifier.defaultWeight())
        Text(
            text = connectionState,
            style = TextStyle(
                color = GlanceTheme.colors.onSurface,
                fontSize = dimensions.maxTextSp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start
            ),
            maxLines = 1
        )
    }
}

@Composable
fun GlanceCustomIconButton(
    imageProvider: ImageProvider,
    contentDescription: String?,
    onClick: Action,
    @DrawableRes rippleOverride: Int,
    @DrawableRes backgroundDrawable: Int,
    dimensions: MediaControlWidgetDimensions,
    modifier: GlanceModifier = GlanceModifier,
    backgroundColor: ColorProvider = GlanceTheme.colors.primary,
    contentColor: ColorProvider = GlanceTheme.colors.onPrimary
) {
    Box(
        modifier = GlanceModifier
            .then(modifier)
            .size(dimensions.maxIconSize)
            .background(
                imageProvider = ImageProvider(backgroundDrawable),
                colorFilter = ColorFilter.tint(
                    colorProvider = backgroundColor
                )
            )
            .clickable(
                onClick = onClick,
                rippleOverride = rippleOverride
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            provider = imageProvider,
            contentDescription = contentDescription,
            modifier = GlanceModifier.size(dimensions.maxIconSize / 2),
            colorFilter = ColorFilter.tint(
                colorProvider = contentColor
            )
        )
    }
}