package com.atharok.btremote.glance

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MediaControlWidgetDimensions {

    val padding: Dp
    val minIconSize: Dp
    val maxIconSize: Dp
    val minTextSp: TextUnit
    val maxTextSp: TextUnit

    private constructor(
        padding: Dp,
        minIconSize: Dp,
        maxIconSize: Dp,
        minTextSp: TextUnit,
        maxTextSp: TextUnit,
    ) {
        this.padding = padding
        this.minTextSp = minTextSp
        this.maxTextSp = maxTextSp
        this.minIconSize = minIconSize
        this.maxIconSize = maxIconSize
    }

    companion object {
        fun calculateDimensions(height: Dp): MediaControlWidgetDimensions {
            return when {
                height > 112.dp -> {
                    MediaControlWidgetDimensions(
                        padding = 12.dp,
                        minIconSize = 40.dp,
                        maxIconSize = 48.dp,
                        minTextSp = 11.sp,
                        maxTextSp = 16.sp
                    )
                }
                height > 100.dp -> {
                    MediaControlWidgetDimensions(
                        padding = 10.dp,
                        minIconSize = 36.dp,
                        maxIconSize = 44.dp,
                        minTextSp = 10.sp,
                        maxTextSp = 15.sp
                    )
                }
                height > 88.dp -> {
                    MediaControlWidgetDimensions(
                        padding = 8.dp,
                        minIconSize = 32.dp,
                        maxIconSize = 40.dp,
                        minTextSp = 10.sp,
                        maxTextSp = 14.sp
                    )
                }
                else -> {
                    MediaControlWidgetDimensions(
                        padding = 6.dp,
                        minIconSize = 28.dp,
                        maxIconSize = 36.dp,
                        minTextSp = 9.sp,
                        maxTextSp = 13.sp
                    )
                }
            }
        }
    }
}