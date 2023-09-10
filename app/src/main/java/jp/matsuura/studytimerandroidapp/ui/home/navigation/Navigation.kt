package jp.matsuura.studytimerandroidapp.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.studytimerandroidapp.ui.category_selection.CategorySelectionScreen
import jp.matsuura.studytimerandroidapp.ui.category_selection.navigation.categorySelectionScreenRoute
import jp.matsuura.studytimerandroidapp.ui.home.HomeScreen
import jp.matsuura.studytimerandroidapp.ui.timer.TimerScreen
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.categoryIdArg
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.timerScreenRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.TimerResultScreen
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.timerScreenResultRoute
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.transactionIdArg

const val homeScreenRoute = "homeScreen"

fun NavGraphBuilder.homeScreens(
    onFABClicked: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToTimer: (Int) -> Unit,
    onNavigateToTimerResult: (transactionId: Int, categoryId: Int) -> Unit,
    onNavigateToHome: () -> Unit,
) {
    composable(homeScreenRoute) {
        HomeScreen(
            onFABClicked = onFABClicked,
        )
    }
    composable(categorySelectionScreenRoute) {
        CategorySelectionScreen(
            onNavigationIconClicked = onNavigateUp,
            onNavigateToTimerScreen = onNavigateToTimer,
        )
    }
    composable(
        route = timerScreenRoute,
        arguments = listOf(
            navArgument(categoryIdArg) { type = NavType.IntType },
        ),
    ) {
        TimerScreen(
            onNavigationIconClicked = onNavigateUp,
            onNavigateToResultScreen = onNavigateToTimerResult,
        )
    }
    composable(
        route = timerScreenResultRoute,
        arguments = listOf(
            navArgument(categoryIdArg) { type = NavType.IntType },
            navArgument(transactionIdArg) { type = NavType.IntType },
        ),
    ) {
        TimerResultScreen(
            onNavigationIconClicked = onNavigateToHome,
            onFinishButtonClicked = onNavigateToHome,
        )
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(homeScreenRoute) {
        popUpTo(homeScreenRoute) { inclusive = true }
    }
}