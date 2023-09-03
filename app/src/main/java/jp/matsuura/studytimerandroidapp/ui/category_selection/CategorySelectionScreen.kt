package jp.matsuura.studytimerandroidapp.ui.category_selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen

const val categorySelectionScreenRoute = "categorySelectionScreen"

fun NavController.navigateToCategorySelectionScreen() {
    navigate(categorySelectionScreenRoute)
}

@Composable
fun CategorySelectionScreen(
    viewModel: CategorySelectionViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategorySelectionScreen(
        uiState = uiState,
        onNavigationIconClicked = onNavigationIconClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategorySelectionScreen(
    uiState: CategorySelectionViewModel.UiState,
    onNavigationIconClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBackTopBar(
                title = stringResource(id = R.string.category_selection_title_text),
                onNavigationIconClicked = {
                    onNavigationIconClicked()
                }
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "CategorySelectionScreen")
        }
    }
}