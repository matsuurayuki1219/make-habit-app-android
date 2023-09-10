package jp.matsuura.studytimerandroidapp.ui.timer_result.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

const val transactionIdArg = "transactionId"
const val categoryIdArg = "categoryId"

const val timerScreenResultRoute = "timerResult/{$categoryIdArg}/{$transactionIdArg}"

class TimerResultScreenArgs(val categoryId: Int, val transactionId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                checkNotNull(savedStateHandle[categoryIdArg]) as Int,
                checkNotNull(savedStateHandle[transactionIdArg]) as Int
            )
}

fun NavController.navigateToTimerResultScreen(transactionId: Int, categoryId: Int) {
    navigate(
        timerScreenResultRoute.replace(
            "{$categoryIdArg}",
            categoryId.toString(),
        ).replace(
            "{$transactionIdArg}",
            transactionId.toString(),
        ),
    )
}