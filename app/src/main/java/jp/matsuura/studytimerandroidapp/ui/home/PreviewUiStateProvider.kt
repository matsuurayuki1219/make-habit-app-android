package jp.matsuura.studytimerandroidapp.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.matsuura.studytimerandroidapp.model.TransactionDetailModel
import java.time.Instant

class PreviewUiStateProvider : PreviewParameterProvider<HomeViewModel.UiState> {
    override val values: Sequence<HomeViewModel.UiState>
        get() = sequenceOf(
            HomeViewModel.UiState(
                isProgressVisible = false,
                transactions = listOf(
                    TransactionDetailModel(
                        transactionId = 1,
                        categoryId = 1,
                        categoryName = "ランニング",
                        startedAt = Instant.now(),
                        endedAt = Instant.now(),
                        durationMillSec = 1000,
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    TransactionDetailModel(
                        transactionId = 2,
                        categoryId = 2,
                        categoryName = "ランニング",
                        startedAt = Instant.now(),
                        endedAt = Instant.now(),
                        durationMillSec = 2000,
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    TransactionDetailModel(
                        transactionId = 3,
                        categoryId = 3,
                        categoryName = "ランニング",
                        startedAt = Instant.now(),
                        endedAt = Instant.now(),
                        durationMillSec = 3000,
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                )
            ),
        )
}