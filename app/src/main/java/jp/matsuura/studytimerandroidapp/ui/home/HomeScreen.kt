package jp.matsuura.studytimerandroidapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.matsuura.studytimerandroidapp.ui.common.AppTopBar

const val homeScreenRoute = "homeScreen"

fun NavGraphBuilder.homeScreens() {
    composable(homeScreenRoute) {
        HomeScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            AppTopBar(title = "習慣化を始めよう")
        }
    ) {
        Text(text = "Home Screen", modifier = Modifier.padding(it))
    }
}