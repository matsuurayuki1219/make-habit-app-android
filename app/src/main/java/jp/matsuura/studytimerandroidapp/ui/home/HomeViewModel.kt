package jp.matsuura.studytimerandroidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.domain.GetAllTransactionUseCase
import jp.matsuura.studytimerandroidapp.model.TransactionDetailModel
import jp.matsuura.studytimerandroidapp.ui.timer.TimerViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTransaction: GetAllTransactionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getAllTransaction().onEach {
            _uiState.value = _uiState.value.copy(
                transactions = it,
            )
        }.catch {
            _uiEvent.send(UiEvent.UnknownError)
        }.launchIn(viewModelScope)
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val transactions: List<TransactionDetailModel>,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
                transactions = emptyList(),
            )
        }
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
    }

}