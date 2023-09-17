package jp.matsuura.makehabit.androidapp.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.makehabit.androidapp.extension.observeWithLifecycle
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import jp.matsuura.makehabit.androidapp.ui.common.AppTopBar
import jp.matsuura.makehabit.androidapp.ui.home.components.TransactionHeaderItem
import jp.matsuura.makehabit.androidapp.ui.home.components.TransactionSectionItem
import jp.matsuura.makehabit.androidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.makehabit.androidapp.ui.timer.PreviewUiStateProvider

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onFABClicked: () -> Unit,
    onNavigateToTimerResult: (transactionId: Int, categoryId: Int) -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is HomeViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onFABClicked = onFABClicked,
        onTransactionItemClicked = onNavigateToTimerResult,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onFABClicked: () -> Unit,
    onTransactionItemClicked: (transactionId: Int, categoryId: Int) -> Unit,
) {
    StudyTimerAndroidAppTheme {
        Scaffold(
            topBar = {
                AppTopBar(title = stringResource(id = R.string.home_title_text))
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onFABClicked,
                ) {
                    Icon(Icons.Filled.Add, "Add")
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }

                items(uiState.uiItems) { transaction ->
                    when (transaction) {
                        is HomeViewModel.UiItem.Header -> {
                            TransactionHeaderItem(date = transaction.date)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        is HomeViewModel.UiItem.Section -> {
                            TransactionSectionItem(
                                transaction = transaction.transaction,
                                onClick = onTransactionItemClicked,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(PreviewUiStateProvider::class) uiState: HomeViewModel.UiState,
) {
    HomeScreen(
        uiState = uiState,
        snackBarHostState = remember { SnackbarHostState() },
        onFABClicked = {},
        onTransactionItemClicked = { _, _ -> },
    )
}