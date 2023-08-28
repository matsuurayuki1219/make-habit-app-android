package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar

const val timerScreenRoute = "timerScreen"

fun NavController.navigateToTimerScreen() {
    navigate(timerScreenRoute)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel(),
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
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        TimerScreen(
            modifier = Modifier.padding(it),
            state = state,
            onTimerButtonClicked = viewModel::onTimerButtonClicked,
        )
    }
}

@Composable
private fun TimerScreen(
    modifier: Modifier,
    state: TimerViewModel.UiState,
    onTimerButtonClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = state.timerText, fontSize = 24.sp)

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = onTimerButtonClicked,
        ) {
            val text = when (state.timerState) {
                TimerViewModel.TimerState.Start -> "中断"
                TimerViewModel.TimerState.Stop -> "開始"
            }
            Text(text = text)
        }
    }
}