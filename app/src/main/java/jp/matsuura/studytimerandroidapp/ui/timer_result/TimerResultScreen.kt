package jp.matsuura.studytimerandroidapp.ui.timer_result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.studytimerandroidapp.extension.observeWithLifecycle
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.common.AppButton
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.studytimerandroidapp.ui.timer_result.components.ScheduleItem
import jp.matsuura.studytimerandroidapp.ui.timer_result.components.SumTimeItem

@Composable
fun TimerResultScreen(
    viewModel: TimerResultViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is TimerResultViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(
                    context.getString(R.string.common_unknown_error_message)
                )
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerResultScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onNavigationIconClicked = onNavigationIconClicked,
        onFinishButtonClicked = onFinishButtonClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerResultScreen(
    uiState: TimerResultViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onNavigationIconClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit,
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
                uiState.transaction?.let { transaction ->

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = transaction.categoryName,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(vertical = 24.dp),
                        )

                        Divider(modifier = Modifier.height(1.dp))

                        ScheduleItem(
                            modifier = Modifier.padding(top = 24.dp, bottom = 30.dp),
                            startData = uiState.transaction.dateOfStartedAt,
                            endData = uiState.transaction.dateOfEndedAt,
                            onStartDateClick = {},
                            onEndDateClick = {},
                            onStartTimeClick = {},
                            onEndTimeClick = {},
                        )

                        Divider(modifier = Modifier.height(1.dp))

                        Text(
                            modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                            text = stringResource(id = R.string.timer_result_sum_title),
                            fontSize = 16.sp,
                        )

                        SumTimeItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            durationMillSec = uiState.transaction.durationMillSec,
                        )

                    }

                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        text = stringResource(id = R.string.common_finish),
                        onClick = onFinishButtonClicked,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimerResultScreenPreview(
    @PreviewParameter(PreviewUiStateProvider::class) uiState: TimerResultViewModel.UiState,
) {
    TimerResultScreen(
        uiState = uiState,
        snackBarHostState = remember { SnackbarHostState() },
        onNavigationIconClicked = {},
        onFinishButtonClicked = {},
    )
}