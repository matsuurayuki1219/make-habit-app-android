package jp.matsuura.makehabit.androidapp.ui.goal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.matsuura.makehabit.androidapp.ui.common.AppTopBar

const val goalSettingScreenRoute = "goalSettingScreen"

fun NavGraphBuilder.goalSettingScreens() {
    composable(goalSettingScreenRoute) {
        GoalSettingScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalSettingScreen() {
    Scaffold(
        topBar = {
            AppTopBar(title = "目標を決めよう")
        }
    ) {
        Text(text = "Goal Setting Screen", modifier = Modifier.padding(it))
    }
}