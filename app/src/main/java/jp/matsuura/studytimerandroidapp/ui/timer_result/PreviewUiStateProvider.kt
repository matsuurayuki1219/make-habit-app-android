package jp.matsuura.studytimerandroidapp.ui.timer_result

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.matsuura.studytimerandroidapp.model.CategoryModel
import jp.matsuura.studytimerandroidapp.model.TransactionDetailModel
import java.time.Instant

class PreviewUiStateProvider : PreviewParameterProvider<TimerResultViewModel.UiState> {
    override val values: Sequence<TimerResultViewModel.UiState>
        get() = sequenceOf(
            TimerResultViewModel.UiState(
                isProgressVisible = false,
                transaction = TransactionDetailModel(
                    transactionId = 1,
                    categoryId = 1,
                    categoryName = "ランニング",
                    startedAt = Instant.now(),
                    endedAt = Instant.now(),
                    durationSec = 1000,
                    createdAt = Instant.now(),
                    updatedAt = Instant.now(),
                )
            ),
        )
}