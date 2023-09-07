package jp.matsuura.studytimerandroidapp.ui.timer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.studytimerandroidapp.domain.GetCategoryUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

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
                _uiState.value = _uiState.value.copy(isProgressVisible = false)
                Timber.d("test")
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
    ) {

        companion object {
            fun initValue(): UiState = UiState(
                isProgressVisible = false,
                millSec = 0,
                timerState = TimerState.Stop,
            )
        }

        private val hour = millSec / 1000 / 60 / 24
        private val minute = millSec / 1000 / 60
        private val second = millSec / 1000 % 60
        private val millSecond = (millSec - minute * 1000 * 60 - second * 1000) / 10

        val timerText = "%02d:%02d:%02d:%02d".format(hour, minute, second, millSecond)

    }

    sealed class TimerState {
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