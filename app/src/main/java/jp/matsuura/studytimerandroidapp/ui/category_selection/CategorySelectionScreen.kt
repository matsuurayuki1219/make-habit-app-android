package jp.matsuura.studytimerandroidapp.ui.category_selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.extension.observeWithLifecycle
import jp.matsuura.studytimerandroidapp.model.CategoryModel
import jp.matsuura.studytimerandroidapp.ui.category_selection.components.CategoryItem
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.common.AppButton
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen

const val categorySelectionScreenRoute = "categorySelectionScreen"

fun NavController.navigateToCategorySelectionScreen() {
    navigate(categorySelectionScreenRoute)
}

@Composable
fun CategorySelectionScreen(
    viewModel: CategorySelectionViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
    onNavigateToTimerScreen: (Int) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is CategorySelectionViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
            is CategorySelectionViewModel.UiEvent.GotoTimerScreen -> {
                onNavigateToTimerScreen(it.id)
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategorySelectionScreen(
        uiState = uiState,
        onNavigationIconClicked = onNavigationIconClicked,
        onCategoryClicked = viewModel::onCategoryClicked,
        onNextButtonClicked = viewModel::onNextButtonClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategorySelectionScreen(
    uiState: CategorySelectionViewModel.UiState,
    onNavigationIconClicked: () -> Unit,
    onCategoryClicked: (CategoryModel) -> Unit,
    onNextButtonClicked: () -> Unit,
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
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(uiState.categories) { category ->
                    CategoryItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        category = category,
                        isSelected = uiState.selectedCategory == category,
                        onClick = {
                            onCategoryClicked(category)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.common_next),
                isEnabled = uiState.selectedCategory != null,
                onClick = onNextButtonClicked,
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}