package jp.matsuura.makehabit.androidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.makehabit.androidapp.domain.GetAllTransactionUseCase
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import jp.matsuura.makehabit.androidapp.model.TransactionModel
import jp.matsuura.makehabit.androidapp.ui.timer.TimerViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllTransaction: GetAllTransactionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getAllTransaction().onEach { transactions ->
            val groups = transactions.groupBy { it.date }.entries.sortedByDescending { it.key }
            val items = mutableListOf<UiItem>()
            groups.forEach {
                items.add(UiItem.Header(date = it.key))
                items.addAll(it.value.sortedByDescending { transaction -> transaction.createdAt }
                    .map { transaction -> UiItem.Section(transaction = transaction) })
            }
            _uiState.value = _uiState.value.copy(
                uiItems = items,
            )
        }.catch {
            _uiEvent.send(UiEvent.UnknownError)
        }.launchIn(viewModelScope)
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val uiItems: List<UiItem>,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
                uiItems = emptyList(),
            )
        }
    }

    sealed interface UiItem {
        data class Header(val date: LocalDate) : UiItem
        data class Section(val transaction: TransactionDetailModel) : UiItem
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
    }

}