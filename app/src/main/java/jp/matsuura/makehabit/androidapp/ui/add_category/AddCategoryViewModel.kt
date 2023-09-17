package jp.matsuura.makehabit.androidapp.ui.add_category

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.makehabit.androidapp.domain.GetAllCategoryUseCase
import jp.matsuura.makehabit.androidapp.domain.InsertCategoryUseCase
import jp.matsuura.makehabit.androidapp.ui.timer_result.TimerResultViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val insertCategory: InsertCategoryUseCase,
    private val getAllCategory: GetAllCategoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTextChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            categoryName = value,
        )
    }

    fun onOkButtonClicked() {
        viewModelScope.launch {
            kotlin.runCatching {
                val categoryName = _uiState.value.categoryName
                val categories = getAllCategory().firstOrNull() ?: return@launch
                val hasSameCategory = categories.any { category ->
                    category.categoryName == categoryName
                }
                if (hasSameCategory) {
                    _uiEvent.send(UiEvent.DuplicateError)
                    _uiState.value = _uiState.value.copy(isError = true)
                    return@launch
                } else {
                    insertCategory(categoryName = categoryName)
                }
                categoryName
            }.onSuccess {
                _uiEvent.send(UiEvent.RegisterComplete(categoryName = it))
                _uiState.value = _uiState.value.copy(isError = false)
            }.onFailure {
                _uiEvent.send(UiEvent.UnknownError)
            }
        }
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val categoryName: String,
        val isError: Boolean,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
                categoryName = "",
                isError = false,
            )
        }
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
        data class RegisterComplete(val categoryName: String) : UiEvent
        object DuplicateError : UiEvent
    }

}