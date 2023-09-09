package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.domain.GetCategoryUseCase
import jp.matsuura.studytimerandroidapp.model.CategoryModel
import jp.matsuura.studytimerandroidapp.ui.timer.navigation.TimerScreenArgs
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class TimerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategory: GetCategoryUseCase,
) : ViewModel() {

    private val args = TimerScreenArgs(savedStateHandle)
    private val categoryId = args.categoryId

    private val _uiState = MutableStateFlow(UiState.initValue())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    private val uiEvent = _uiEvent.receiveAsFlow()

    private var timer = Timer()

    private val timerTask: TimerTask.() -> Unit = {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                millSec = uiState.value.millSec.plus(1),
            )
        }
    }

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = _uiState.value.copy(isProgressVisible = true)
                getCategory(categoryId = categoryId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isProgressVisible = false,
                    category = it,
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                _uiEvent.send(UiEvent.UnknownError)
            }
        }
    }

    fun onTimerButtonClicked() {
        when (_uiState.value.timerState) {
            TimerState.Start -> stopTimer()
            TimerState.Stop -> startTimer()
            TimerState.Initial -> startTimer()
        }
    }

    private fun startTimer() {
        _uiState.value = _uiState.value.copy(
            timerState = TimerState.Start,
        )
        timer = Timer()
        timer.scheduleAtFixedRate(0, TIMER_PERIOD, timerTask)
    }

    private fun stopTimer() {
        _uiState.value = _uiState.value.copy(
            timerState = TimerState.Stop,
        )
        timer.cancel()
    }

    data class UiState(
        val isProgressVisible: Boolean,
        val millSec: Int,
        val timerState: TimerState,
        val category: CategoryModel?,
    ) {

        companion object {
            fun initValue(): UiState = UiState(
                isProgressVisible = false,
                millSec = 0,
                timerState = TimerState.Initial,
                category = null,
            )
        }

        val hour = millSec / 1000 / 60 / 24
        val minute = millSec / 1000 / 60
        val second = millSec / 1000 % 60

    }

    sealed class TimerState {
        object Initial : TimerState()
        object Start : TimerState()
        object Stop : TimerState()
    }

    sealed interface UiEvent {
        object UnknownError : UiEvent
    }

    companion object {
        private const val TIMER_PERIOD = 1L
    }
}