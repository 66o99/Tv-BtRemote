package com.atharok.btremote.ui.views.remoteNavigation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.common.utils.ArcShape
import com.atharok.btremote.ui.components.RemoteButtonSurface
import com.atharok.btremote.ui.components.StatefulRemoteButton
import com.atharok.btremote.ui.theme.dimensionElevation2

private val TopArcShape = ArcShape(-45f, -90f)
private val BottomArcShape = ArcShape(45f, 90f)
private val LeftArcShape = ArcShape(135f, 90f)
private val RightArcShape = ArcShape(-45f, 90f)

@Composable
fun RemoteDirectionalPadNavigation(
    upTouchDown: () -> Unit,
    downTouchDown: () -> Unit,
    leftTouchDown: () -> Unit,
    rightTouchDown: () -> Unit,
    pickTouchDown: () -> Unit,
    directionTouchUp: () -> Unit,
    pickTouchUp: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = dimensionElevation2()
) {
    Box(
        modifier = modifier.shadow(
            elevation = elevation,
            shape = CircleShape
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // ---- Top ----
            RemoteButtonSurface(
                modifier = Modifier.fillMaxSize(),
                shape = TopArcShape,
                elevation = elevation
            ) {
                DPadButton(touchDown = upTouchDown, touchUp = directionTouchUp)
            }

            // ---- Bottom ----
            RemoteButtonSurface(
                modifier = Modifier.fillMaxSize(),
                shape = BottomArcShape,
                elevation = elevation
            ) {
                DPadButton(touchDown = downTouchDown, touchUp = directionTouchUp)
            }

            // ---- Left ----
            RemoteButtonSurface(
                modifier = Modifier.fillMaxSize(),
                shape = LeftArcShape,
                elevation = elevation
            ) {
                DPadButton(touchDown = leftTouchDown, touchUp = directionTouchUp)
            }

            // ---- Right ----
            RemoteButtonSurface(
                modifier = Modifier.fillMaxSize(),
                shape = RightArcShape,
                elevation = elevation
            ) {
                DPadButton(touchDown = rightTouchDown, touchUp = directionTouchUp)
            }

            // ---- Center ----
            RemoteButtonSurface(
                modifier = Modifier.fillMaxSize(0.3333f),
                shape = CircleShape,
                elevation = elevation
            ) {
                DPadButton(touchDown = pickTouchDown, touchUp = pickTouchUp)
            }
        }

        // ---- Icons ----
        Column(modifier = Modifier.wrapContentSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(Modifier.weight(1f).padding(8.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = AppIcons.Up,
                    contentDescription = stringResource(id = R.string.up),
                )
                Spacer(Modifier.weight(1f).padding(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = AppIcons.Left,
                    contentDescription = stringResource(id = R.string.left)
                )
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = AppIcons.Pick,
                    contentDescription = stringResource(id = R.string.pick)
                )
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = AppIcons.Right,
                    contentDescription = stringResource(id = R.string.right)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(0.dp).weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(Modifier.weight(1f).padding(8.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = AppIcons.Down,
                    contentDescription = stringResource(id = R.string.down)
                )
                Spacer(Modifier.weight(1f).padding(8.dp))
            }
        }
    }
}

@Composable
private fun DPadButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit
) {
    StatefulRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .clickable(
                    interactionSource = it,
                    indication = LocalIndication.current,
                    onClick = {}
                )
        )
    }
}