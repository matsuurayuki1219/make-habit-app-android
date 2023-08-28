package jp.matsuura.studytimerandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.matsuura.studytimerandroidapp.ui.goal.goalSettingScreenRoute
import jp.matsuura.studytimerandroidapp.ui.goal.goalSettingScreens
import jp.matsuura.studytimerandroidapp.ui.home.homeScreenRoute
import jp.matsuura.studytimerandroidapp.ui.home.homeScreens
import jp.matsuura.studytimerandroidapp.ui.other.otherScreenRoute
import jp.matsuura.studytimerandroidapp.ui.other.otherScreens
import jp.matsuura.studytimerandroidapp.ui.theme.StudyTimerAndroidAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyTimerAndroidAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        AppNavigationBar(modifier = Modifier, navController = navController)
                    },
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    AppNavHost(navController = navController, modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
private fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = homeScreenRoute,
        modifier = modifier,
    ) {
        homeScreens()
        goalSettingScreens()
        otherScreens()
    }
}

@Composable
private fun AppNavigationBar(
    modifier: Modifier,
    navController: NavHostController,
) {
    val screens = listOf(
        NavigationBarItem.Home,
        NavigationBarItem.GoalSetting,
        NavigationBarItem.Other,
    )
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Color.White
                ),
            )
        }
    }
}

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : NavigationBarItem(
        route = homeScreenRoute,
        title = "Home",
        icon = Icons.Outlined.Home,
    )

    object GoalSetting : NavigationBarItem(
        route = goalSettingScreenRoute,
        title = "Goal",
        icon = Icons.Outlined.Star,
    )

    object Other : NavigationBarItem(
        route = otherScreenRoute,
        title = "Other",
        icon = Icons.Outlined.Settings,
    )
}