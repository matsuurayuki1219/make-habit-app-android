package jp.matsuura.makehabit.androidapp.ui.timer_result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.makehabit.androidapp.domain.GetDetailTransactionUseCase
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import jp.matsuura.makehabit.androidapp.ui.timer.TimerViewModel
import jp.matsuura.makehabit.androidapp.ui.timer_result.navigation.TimerResultScreenArgs
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class TimerResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailTransaction: GetDetailTransactionUseCase,
) : ViewModel() {

    private val args = TimerResultScreenArgs(savedStateHandle)
    private val transactionId = args.transactionId
    private val categoryId = args.categoryId

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                getDetailTransaction(
                    transactionId = transactionId,
                    categoryId = categoryId,
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isProgressVisible = false,
                    transaction = it,
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                _uiEvent.send(UiEvent.UnknownError)
            }
        }
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val transaction: TransactionDetailModel?,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
                transaction = null,
            )
        }
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
    }
}