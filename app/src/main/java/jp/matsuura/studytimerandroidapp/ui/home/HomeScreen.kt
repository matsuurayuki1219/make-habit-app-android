package jp.matsuura.studytimerandroidapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.studytimerandroidapp.NavigationBarItem
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.extension.observeWithLifecycle
import jp.matsuura.studytimerandroidapp.model.TransactionDetailModel
import jp.matsuura.studytimerandroidapp.ui.category_selection.CategorySelectionScreen
import jp.matsuura.studytimerandroidapp.ui.category_selection.navigation.categorySelectionScreenRoute
import jp.matsuura.studytimerandroidapp.ui.common.AppTopBar
import jp.matsuura.studytimerandroidapp.ui.home.components.TransactionItem
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen
import jp.matsuura.studytimerandroidapp.ui.timer.TimerViewModel
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.categoryIdArg
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.timerScreenRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.TimerResultScreen
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.timerScreenResultRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.transactionIdArg

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onFABClicked: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is HomeViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onFABClicked = onFABClicked,
        // TODO: 後で画面遷移を実装する。
        onTransactionItemClicked = {},
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onFABClicked: () -> Unit,
    onTransactionItemClicked: (TransactionDetailModel) -> Unit,
) {
    StudyTimerAndroidAppTheme {
        Scaffold(
            topBar = {
                AppTopBar(title = stringResource(id = R.string.home_title_text))
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onFABClicked,
                ) {
                    Icon(Icons.Filled.Add, "Add")
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }

                items(uiState.transactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onClick = onTransactionItemClicked,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}