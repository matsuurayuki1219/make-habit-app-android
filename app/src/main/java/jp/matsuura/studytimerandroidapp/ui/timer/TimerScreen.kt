package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar

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
        onTimerButtonClicked = {},
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
        Column(modifier = Modifier.padding(it)) {
            Text(text = "TimerScreen")
        }
    }
}