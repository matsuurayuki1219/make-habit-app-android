package jp.matsuura.studytimerandroidapp.ui.category_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.domain.GetAllCategoryUseCase
import jp.matsuura.studytimerandroidapp.domain.InsertCategoryUseCase
import jp.matsuura.studytimerandroidapp.model.CategoryModel
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
class CategorySelectionViewModel @Inject constructor(
    private val getAllCategory: GetAllCategoryUseCase,
    private val insert: InsertCategoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        fetchCategory()
    }

    private fun fetchCategory() {
        getAllCategory().onEach {
            _uiState.value = _uiState.value.copy(categories = it)
        }.catch {
            _uiEvent.send(UiEvent.UnknownError)
        }.launchIn(viewModelScope)
    }

    fun onCategoryClicked(category: CategoryModel) {
        val selectedCategory = _uiState.value.selectedCategory
        if (selectedCategory != null && selectedCategory == category) return
        _uiState.value = _uiState.value.copy(
            selectedCategory = category,
        )
    }

    fun onNextButtonClicked() {
        val selectedCategory = _uiState.value.selectedCategory
        if (selectedCategory != null) {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.GotoTimerScreen(id = selectedCategory.id))
            }
        }
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val categories: List<CategoryModel>,
        val selectedCategory: CategoryModel?,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
                categories = emptyList(),
                selectedCategory = null,
            )
        }
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
        data class GotoTimerScreen(val id: Int) : UiEvent
    }
}