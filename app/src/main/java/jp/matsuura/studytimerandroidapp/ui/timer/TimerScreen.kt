package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.studytimerandroidapp.extension.observeWithLifecycle
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.studytimerandroidapp.ui.timer.components.TimerButton
import jp.matsuura.studytimerandroidapp.ui.timer.components.TimerButtonState
import jp.matsuura.studytimerandroidapp.ui.timer.components.TimerItem

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
    onNavigateToResultScreen: (transactionId: Int, categoryId: Int) -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is TimerViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
            is TimerViewModel.UiEvent.Success -> {
                onNavigateToResultScreen(it.transactionId, it.categoryId)
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onTimerButtonClicked = viewModel::onTimerButtonClicked,
        onNavigationIconClicked = onNavigationIconClicked,
        onFinishButtonClicked = viewModel::onTimerFinishClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerScreen(
    uiState: TimerViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onTimerButtonClicked: () -> Unit,
    onNavigationIconClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit,
) {
    StudyTimerAndroidAppTheme {
        Scaffold(
            topBar = {
                AppBackTopBar(
                    title = stringResource(id = R.string.timer_title_text),
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                uiState.category?.let { category ->
                    Text(text = category.categoryName, fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(24.dp))
                TimerItem(
                    modifier = Modifier.weight(1f),
                    hours = uiState.hour,
                    minutes = uiState.minute,
                    seconds = uiState.second,
                )
                Spacer(modifier = Modifier.height(24.dp))
                TimerButton(
                    state = when (uiState.timerState) {
                        TimerViewModel.TimerState.Initial -> TimerButtonState.INITIAL
                        TimerViewModel.TimerState.Start -> TimerButtonState.IS_CALCULATING
                        TimerViewModel.TimerState.Stop -> TimerButtonState.IS_STOPPING
                    },
                    onClick = onTimerButtonClicked,
                    onFinish = onFinishButtonClicked,
                )
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimerScreenPreviews(
    @PreviewParameter(PreviewUiStateProvider::class) uiState: TimerViewModel.UiState,
) {
    TimerScreen(
        uiState = uiState,
        snackBarHostState =  remember { SnackbarHostState() },
        onTimerButtonClicked = {},
        onNavigationIconClicked = {},
        onFinishButtonClicked = {},
    )
}
