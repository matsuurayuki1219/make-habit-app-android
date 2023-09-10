package jp.matsuura.studytimerandroidapp.ui.timer_result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.extension.observeWithLifecycle
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme

@Composable
fun TimerResultScreen(
    viewModel: TimerResultViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is TimerResultViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerResultScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onNavigationIconClicked = onNavigationIconClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerResultScreen(
    uiState: TimerResultViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onNavigationIconClicked: () -> Unit,
) {
    StudyTimerAndroidAppTheme {
        Scaffold(
            topBar = {
                AppBackTopBar(
                    title = stringResource(id = R.string.timer_result_title_text),
                    onNavigationIconClicked = {
                        onNavigationIconClicked()
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {
            Column(
                modifier = Modifier.padding(it),
            ) {
                Text(text = "Timer Result Screen")
                uiState.transaction?.let { transaction ->
                    Text(text = "transactionId: ${transaction.transactionId}")
                    Text(text = "categoryId: ${transaction.categoryId}")
                    Text(text = "transactionName: ${transaction.categoryName}")
                    Text(text = "durationSec: ${transaction.durationSec}")
                }
            }
        }
    }
}