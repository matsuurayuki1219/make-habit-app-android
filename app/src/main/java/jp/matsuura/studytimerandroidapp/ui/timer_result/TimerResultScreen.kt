package jp.matsuura.studytimerandroidapp.ui.timer_result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme

@Composable
fun TimerResultScreen(
    viewModel: TimerResultViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimerResultScreen(
        uiState = uiState,
        onNavigationIconClicked = onNavigationIconClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimerResultScreen(
    uiState: TimerResultViewModel.UiState,
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
        ) {
            Column(
                modifier = Modifier.padding(it),
            ) {
                Text(text = "Timer Result Screen")
            }
        }
    }
}