package jp.matsuura.studytimerandroidapp.ui.add_category.navigation

import androidx.navigation.NavController

const val addCategoryScreenRoute = "addCategoryScreen"

fun NavController.navigateToAddCategoryScreen() {
    navigate(addCategoryScreenRoute)
}