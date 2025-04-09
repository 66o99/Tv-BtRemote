package com.atharok.btremote.ui.screens

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.AppIcons
import com.atharok.btremote.domain.entities.ThirdLibrary
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.MaterialFilledTonalButton
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextMedium
import com.atharok.btremote.ui.components.TextNormalSecondary

@Composable
fun ThirdLibrariesScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatelessThirdLibrariesScreen(
        libraries = ThirdLibrary.entries,
        navigateUp = navigateUp,
        modifier = modifier
    )
}

@Composable
fun StatelessThirdLibrariesScreen(
    libraries : List<ThirdLibrary>,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.third_party_library),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        }
    ) { innerPadding ->

        LazyColumn(
            contentPadding = innerPadding
        ) {
            itemsIndexed(libraries) { index, item ->
                ThirdLibraryItem(
                    library = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_max))
                )

                if (index < libraries.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun ThirdLibraryItem(
    library: ThirdLibrary,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TextMedium(text = stringResource(id = library.title))
        TextNormalSecondary(text = stringResource(id = library.id))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonOpenInBrowser(
                textId = library.codeHost,
                urlId = library.codeUrl
            )

            ButtonOpenInBrowser(
                textId = library.license,
                urlId = library.licenseUrl
            )
        }
    }
}

@Composable
private fun ButtonOpenInBrowser(
    @StringRes textId: Int,
    @StringRes urlId: Int,
    context: Context = LocalContext.current,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    MaterialFilledTonalButton(
        onClick = {
            uriHandler.openUri(context.getString(urlId))
        },
        text = stringResource(id = textId),
        icon = AppIcons.OpenInBrowser
    )
}

