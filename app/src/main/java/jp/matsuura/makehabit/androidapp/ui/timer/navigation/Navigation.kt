package jp.matsuura.makehabit.androidapp.ui.timer.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

const val categoryIdArg = "categoryId"

const val timerScreenRoute = "timer/{$categoryIdArg}"

class TimerScreenArgs(val categoryId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[categoryIdArg]) as Int)
}

fun NavController.navigateToTimerScreen(categoryId: Int) {
    navigate(
        timerScreenRoute.replace(
            "{$categoryIdArg}",
            categoryId.toString(),
        ),
    )
}
