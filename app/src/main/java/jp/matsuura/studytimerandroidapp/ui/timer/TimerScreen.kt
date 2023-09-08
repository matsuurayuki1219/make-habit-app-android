package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.timer.components.TimerButton
import jp.matsuura.studytimerandroidapp.ui.timer.components.TimerButtonState

const val categoryIdArg = "categoryId"

const val timerScreenRoute = "timer/{$categoryIdArg}"

class TimerScreenArgs(val categoryId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[categoryIdArg]) as Int)
}

fun NavController.navigateToTimerScreen(categoryId: Int) {
    navigate(
        timerScreenRoute.replace(
            "{$categoryIdArg}",
            categoryId.toString(),
        ),
    )
}

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerScreen(
        uiState = uiState,
        onTimerButtonClicked = viewModel::onTimerButtonClicked,
        onNavigationIconClicked = onNavigationIconClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerScreen(
    uiState: TimerViewModel.UiState,
    onTimerButtonClicked: () -> Unit,
    onNavigationIconClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBackTopBar(
                title = stringResource(id = R.string.timer_title_text),
                onNavigationIconClicked = {
                    onNavigationIconClicked()
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            uiState.category?.let { category ->
                Text(text = category.categoryName)
            }

            Spacer(modifier = Modifier.weight(1f))
            TimerButton(
                state = when (uiState.timerState) {
                    TimerViewModel.TimerState.Initial -> TimerButtonState.INITIAL
                    TimerViewModel.TimerState.Start -> TimerButtonState.IS_CALCULATING
                    TimerViewModel.TimerState.Stop -> TimerButtonState.IS_STOPPING
                },
                onClick = onTimerButtonClicked,
            )
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}