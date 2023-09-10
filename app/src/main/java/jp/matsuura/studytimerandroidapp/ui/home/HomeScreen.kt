package jp.matsuura.studytimerandroidapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.studytimerandroidapp.ui.category_selection.CategorySelectionScreen
import jp.matsuura.studytimerandroidapp.ui.category_selection.navigation.categorySelectionScreenRoute
import jp.matsuura.studytimerandroidapp.ui.common.AppTopBar
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.categoryIdArg
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.timerScreenRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.TimerResultScreen
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.timerScreenResultRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.transactionIdArg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onFABClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(title = "習慣化を始めよう")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFABClicked() },
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        Text(text = "Home Screen", modifier = Modifier.padding(it))
    }
}