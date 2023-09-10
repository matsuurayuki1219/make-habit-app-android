package jp.matsuura.studytimerandroidapp.ui.timer_result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.domain.GetCategoryUseCase
import jp.matsuura.studytimerandroidapp.ui.timer.TimerViewModel
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.TimerScreenArgs
import jp.matsuura.studytimerandroidapp.ui.timer_result.navigation.TimerResultScreenArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TimerResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoryUseCase: GetCategoryUseCase,
) : ViewModel() {

    private val args = TimerResultScreenArgs(savedStateHandle)
    private val categoryId = args.categoryId

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val isProgressVisible: Boolean,
    ) {
        companion object {
            fun initValue() = UiState(
                isProgressVisible = false,
            )
        }
    }
}