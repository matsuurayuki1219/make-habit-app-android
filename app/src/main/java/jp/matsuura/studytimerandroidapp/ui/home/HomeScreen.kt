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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.matsuura.studytimerandroidapp.ui.category_selection.CategorySelectionScreen
import jp.matsuura.studytimerandroidapp.ui.category_selection.categorySelectionScreenRoute
import jp.matsuura.studytimerandroidapp.ui.common.AppTopBar
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen
import jp.matsuura.studytimerandroidapp.ui.timer.timerScreenRoute

const val homeScreenRoute = "homeScreen"

fun NavGraphBuilder.homeScreens(
    onFABClicked: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable(homeScreenRoute) {
        HomeScreen(
            onFABClicked = onFABClicked,
        )
    }
    composable(categorySelectionScreenRoute) {
        CategorySelectionScreen(
            onNavigationIconClicked = onNavigateUp,
        )
    }
    composable(timerScreenRoute) {
        TimerScreen(
            onNavigationIconClicked = onNavigateUp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
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