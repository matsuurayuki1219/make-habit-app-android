package jp.matsuura.makehabit.androidapp.ui.add_category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.makehabit.androidapp.extension.observeWithLifecycle
import jp.matsuura.makehabit.androidapp.ui.category_selection.components.CategoryItem
import jp.matsuura.makehabit.androidapp.ui.common.AppBackTopBar
import jp.matsuura.makehabit.androidapp.ui.common.AppButton
import jp.matsuura.makehabit.androidapp.ui.theme.StudyTimerAndroidAppTheme
import jp.matsuura.makehabit.androidapp.ui.timer_result.TimerResultViewModel

@Composable
fun AddCategoryScreen(
    viewModel: AddCategoryViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is AddCategoryViewModel.UiEvent.UnknownError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }

            is AddCategoryViewModel.UiEvent.RegisterComplete -> {
                // TODO: スナックバーを表示させる
                onNavigateUp()
            }

            is AddCategoryViewModel.UiEvent.DuplicateError -> {
                snackBarHostState.showSnackbar(context.getString(R.string.add_category_duplicate_error_message))
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddCategoryScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onNavigationIconClicked = onNavigationIconClicked,
        onTextChanged = viewModel::onTextChanged,
        onButtonClicked = viewModel::onOkButtonClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCategoryScreen(
    uiState: AddCategoryViewModel.UiState,
    snackBarHostState: SnackbarHostState,
    onNavigationIconClicked: () -> Unit,
    onTextChanged: (String) -> Unit,
    onButtonClicked: () -> Unit,
) {
    StudyTimerAndroidAppTheme {
        Scaffold(
            topBar = {
                AppBackTopBar(
                    title = stringResource(id = R.string.add_category_title_text),
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
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 30.dp),
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = stringResource(id = R.string.add_category_message), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.categoryName,
                    singleLine = true,
                    onValueChange = onTextChanged,
                    isError = uiState.isError,
                )
                Spacer(modifier = Modifier.weight(1f))
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.common_ok),
                    onClick = onButtonClicked,
                    isEnabled = uiState.categoryName.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}