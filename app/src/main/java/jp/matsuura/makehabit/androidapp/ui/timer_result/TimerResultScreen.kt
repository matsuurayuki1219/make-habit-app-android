package jp.matsuura.makehabit.androidapp.ui.timer_result

import android.app.Dialog
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
import jp.matsuura.makehabit.androidapp.extension.observeWithLifecycle
import jp.matsuura.makehabit.androidapp.model.TimeModel
import jp.matsuura.makehabit.androidapp.ui.common.AppBackTopBar
import jp.matsuura.makehabit.androidapp.ui.common.AppButton
import jp.matsuura.makehabit.androidapp.ui.common.AppDataPicker
import jp.matsuura.makehabit.androidapp.ui.common.AppDialogState
import jp.matsuura.makehabit.androidapp.ui.common.AppTimePicker
import jp.matsuura.makehabit.androidapp.ui.common.rememberAppDialogState
import jp.matsuura.makehabit.androidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.makehabit.androidapp.ui.timer_result.components.ScheduleItem
import jp.matsuura.makehabit.androidapp.ui.timer_result.components.SumTimeItem

sealed interface DialogType {
    data class StartDatePicker(val currentData: Long?) : DialogType
    data class EndDatePicker(val currentData: Long?) : DialogType
    data class StartTimePicker(val currentTime: Long?) : DialogType
    data class EndTimePicker(val currentTime: Long?) : DialogType
}

@Composable
fun TimerResultScreen(
    viewModel: TimerResultViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val dialogState = rememberAppDialogState<DialogType>()

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is TimerResultViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(
                    context.getString(R.string.common_unknown_error_message)
                )
            }

            is TimerResultViewModel.UiEvent.StartDateClicked -> {
                dialogState.show(DialogType.StartDatePicker(it.currentData))
            }

            is TimerResultViewModel.UiEvent.EndDateClicked -> {
                dialogState.show(DialogType.EndDatePicker(it.currentData))
            }

            is TimerResultViewModel.UiEvent.StartTimeClicked -> {
                dialogState.show(DialogType.StartTimePicker(it.currentData))
            }

            is TimerResultViewModel.UiEvent.EndTimeClicked -> {
                dialogState.show(DialogType.EndTimePicker(it.currentData))
            }
        }
    }

    if (dialogState.isShow) {
        val dialogType = dialogState.type ?: return
        DialogHandler(
            type = dialogType,
            onStartDateConfirmed = viewModel::onStartDateConfirmed,
            onEndDateConfirmed = viewModel::onEndDateConfirmed,
            onStartTimeConfirmed = viewModel::onStartTimeConfirmed,
            onEndTimeConfirmed = viewModel::onEndTimeConfirmed,
            onDismiss = dialogState::dismiss,
        )
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerResultScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onNavigationIconClicked = onNavigationIconClicked,
        onFinishButtonClicked = onFinishButtonClicked,
        onStartDateClick = viewModel::onStartDateClicked,
        onEndDateClick = viewModel::onEndDateClicked,
        onStartTimeClick = viewModel::onStartTimeClicked,
        onEndTimeClick = viewModel::onEndTimeClicked,
    )

}

@Composable
private fun TimerResultScreen(
    uiState: TimerResultViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onNavigationIconClicked: () -> Unit,
    onFinishButtonClicked: () -> Unit,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
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
                            onStartDateClick = onStartDateClick,
                            onEndDateClick = onEndDateClick,
                            onStartTimeClick = onStartTimeClick,
                            onEndTimeClick = onEndTimeClick,
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

@Composable
private fun DialogHandler(
    type: DialogType,
    onStartDateConfirmed: (Long?) -> Unit,
    onEndDateConfirmed: (Long?) -> Unit,
    onStartTimeConfirmed: (TimeModel) -> Unit,
    onEndTimeConfirmed: (TimeModel) -> Unit,
    onDismiss: () -> Unit,
) {
    when (type) {
        is DialogType.StartDatePicker -> {
            AppDataPicker(
                currentDate = type.currentData,
                onUpdateDate = onStartDateConfirmed,
                onDismiss = onDismiss,
            )
        }

        is DialogType.EndDatePicker -> {
            AppDataPicker(
                currentDate = type.currentData,
                onUpdateDate = onEndDateConfirmed,
                onDismiss = onDismiss,
            )
        }

        is DialogType.StartTimePicker -> {
            AppTimePicker(
                currentTime = type.currentTime,
                onUpdateTime = onStartTimeConfirmed,
                onDismiss = onDismiss,
            )
        }

        is DialogType.EndTimePicker -> {
            AppTimePicker(
                currentTime = type.currentTime,
                onUpdateTime = onEndTimeConfirmed,
                onDismiss = onDismiss,
            )
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
        onStartDateClick = {},
        onEndDateClick = {},
        onStartTimeClick = {},
        onEndTimeClick = {},
    )
}