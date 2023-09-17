package jp.matsuura.makehabit.androidapp.ui.category_selection.navigation

import androidx.navigation.NavController

const val categorySelectionScreenRoute = "categorySelectionScreen"

fun NavController.navigateToCategorySelectionScreen() {
    navigate(categorySelectionScreenRoute)
}