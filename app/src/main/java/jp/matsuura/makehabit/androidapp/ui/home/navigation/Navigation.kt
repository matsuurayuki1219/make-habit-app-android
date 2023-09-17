package jp.matsuura.makehabit.androidapp.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.makehabit.androidapp.ui.add_category.AddCategoryScreen
import jp.matsuura.makehabit.androidapp.ui.add_category.navigation.addCategoryScreenRoute
import jp.matsuura.makehabit.androidapp.ui.category_selection.CategorySelectionScreen
import jp.matsuura.makehabit.androidapp.ui.category_selection.navigation.categorySelectionScreenRoute
import jp.matsuura.makehabit.androidapp.ui.home.HomeScreen
import jp.matsuura.makehabit.androidapp.ui.timer.TimerScreen
import jp.matsuura.makehabit.androidapp.ui.timer.navigation.categoryIdArg
import jp.matsuura.makehabit.androidapp.ui.timer.navigation.timerScreenRoute
import jp.matsuura.makehabit.androidapp.ui.timer_result.TimerResultScreen
import jp.matsuura.makehabit.androidapp.ui.timer_result.navigation.timerScreenResultRoute
import jp.matsuura.makehabit.androidapp.ui.timer_result.navigation.transactionIdArg

const val homeScreenRoute = "homeScreen"

fun NavGraphBuilder.homeScreens(
    onFABClicked: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToTimer: (Int) -> Unit,
    onNavigateToTimerResult: (transactionId: Int, categoryId: Int) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToAddCategory: () -> Unit,
) {
    composable(homeScreenRoute) {
        HomeScreen(
            onFABClicked = onFABClicked,
            onNavigateToTimerResult = onNavigateToTimerResult,
        )
    }
    composable(categorySelectionScreenRoute) {
        CategorySelectionScreen(
            onNavigationIconClicked = onNavigateUp,
            onNavigateToTimerScreen = onNavigateToTimer,
            onNavigateToAddCategoryScreen = onNavigateToAddCategory,
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
    composable(addCategoryScreenRoute) {
        AddCategoryScreen(
            onNavigationIconClicked = onNavigateUp,
            onNavigateUp = onNavigateUp,
        )
    }
}

fun NavController.navigateToHomeScreen() {
    navigate(homeScreenRoute) {
        popUpTo(homeScreenRoute) { inclusive = true }
    }
}