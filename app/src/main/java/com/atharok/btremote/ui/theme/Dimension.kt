package com.atharok.btremote.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.atharok.btremote.R

@Composable
@ReadOnlyComposable
fun dimensionElevation1(): Dp = dimensionResource(id = R.dimen.elevation_1)

@Composable
@ReadOnlyComposable
fun dimensionElevation2(): Dp = dimensionResource(id = R.dimen.elevation_2)

@Composable
@ReadOnlyComposable
fun dimensionElevation3(): Dp = dimensionResource(id = R.dimen.elevation_3)

@Composable
@ReadOnlyComposable
fun dimensionElevation4(): Dp = dimensionResource(id = R.dimen.elevation_4)

@Composable
@ReadOnlyComposable
fun dimensionShadowElevation(): Dp = dimensionResource(id = R.dimen.shadow_elevation)