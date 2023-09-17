package jp.matsuura.makehabit.androidapp.ui.add_category.navigation

import androidx.navigation.NavController

const val addCategoryScreenRoute = "addCategoryScreen"

fun NavController.navigateToAddCategoryScreen() {
    navigate(addCategoryScreenRoute)
}