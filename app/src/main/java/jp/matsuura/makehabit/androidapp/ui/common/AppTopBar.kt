package jp.matsuura.makehabit.androidapp.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
) {
    TopAppBar(
        title = { Text(text = title) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBackTopBar(
    title: String,
    onNavigationIconClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClicked.invoke() }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCloseTopBar(
    title: String,
    onNavigationIconClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClicked.invoke() }
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        },
    )
}