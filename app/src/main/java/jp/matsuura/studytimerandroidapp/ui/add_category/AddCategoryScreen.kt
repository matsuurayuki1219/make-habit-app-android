package jp.matsuura.studytimerandroidapp.ui.add_category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.ui.category_selection.components.CategoryItem
import jp.matsuura.studytimerandroidapp.ui.common.AppBackTopBar
import jp.matsuura.studytimerandroidapp.ui.common.AppButton
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme

@Composable
fun AddCategoryScreen(
    viewModel: AddCategoryViewModel = hiltViewModel(),
    onNavigationIconClicked: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    AddCategoryScreen(
        snackBarHostState = snackBarHostState,
        onNavigationIconClicked = onNavigationIconClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCategoryScreen(
    snackBarHostState: SnackbarHostState,
    onNavigationIconClicked: () -> Unit,
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
                modifier = Modifier.padding(it),
            ) {
                Text(text = "Add Category Screen")
            }
        }
    }
}