package jp.matsuura.studytimerandroidapp.ui.other

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.matsuura.studytimerandroidapp.ui.common.AppTopBar

const val otherScreenRoute = "otherScreen"

fun NavGraphBuilder.otherScreens() {
    composable(otherScreenRoute) {
        OtherScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen() {
    Scaffold(
        topBar = {
            AppTopBar(title = "その他")
        }
    ) {
        Text(text = "Other Screen", modifier = Modifier.padding(it))
    }
}