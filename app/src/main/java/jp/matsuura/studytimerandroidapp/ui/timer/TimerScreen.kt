package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar

const val timerScreenRoute = "timerScreen"

fun NavController.navigateToTimerScreen() {
    navigate(timerScreenRoute)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    onNavigationIconClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBackTopBar(
                title = "時間を計測しよう",
                onNavigationIconClicked = {
                    onNavigationIconClicked()
                }
            )
        },
    ) {
        Text(text = "Timer Screen", modifier = Modifier.padding(it))
    }
}