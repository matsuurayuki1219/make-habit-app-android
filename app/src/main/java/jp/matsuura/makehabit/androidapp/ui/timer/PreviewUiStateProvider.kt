package jp.matsuura.makehabit.androidapp.ui.timer

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import java.time.Instant

class PreviewUiStateProvider : PreviewParameterProvider<TimerViewModel.UiState> {
    override val values: Sequence<TimerViewModel.UiState>
        get() = sequenceOf(
            TimerViewModel.UiState(
                isProgressVisible = false,
                millSec = 100,
                timerState = TimerViewModel.TimerState.Initial,
                category = CategoryModel(
                    id = 1,
                    categoryName = "ランニング",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now(),
                ),
            ),
        )
}