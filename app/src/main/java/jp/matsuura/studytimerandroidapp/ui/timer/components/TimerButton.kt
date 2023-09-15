package jp.matsuura.studytimerandroidapp.ui.timer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.studytimerandroidapp.ui.common.AppIconCircleButton

enum class TimerButtonState {
    INITIAL, IS_CALCULATING, IS_STOPPING,
}

@Composable
fun TimerButton(
    modifier: Modifier = Modifier,
    state: TimerButtonState,
    onClick: () -> Unit,
    onFinish: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state) {
            TimerButtonState.INITIAL -> {
                StartButton (
                    onClick = onClick
                )
            }
            TimerButtonState.IS_CALCULATING -> {
                StopButton (
                    onClick = onClick
                )
            }
            TimerButtonState.IS_STOPPING -> {
                Row(
                    modifier = modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    StopButton (
                        onClick = onFinish
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    StartButton (
                        onClick = onClick
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AppIconCircleButton(
        modifier = modifier,
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_play)
    )
}

@Composable
private fun StopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AppIconCircleButton(
        modifier = modifier,
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_stop)
    )
}

@Preview(showBackground = true)
@Composable
private fun TimerButtonInitialPreviews() {
    TimerButton(
        state = TimerButtonState.INITIAL,
        onClick = {},
        onFinish = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun TimerButtonIsCalculatingPreviews() {
    TimerButton(
        state = TimerButtonState.IS_CALCULATING,
        onClick = {},
        onFinish = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun TimerButtonIsStoppingPreviews() {
    TimerButton(
        state = TimerButtonState.IS_STOPPING,
        onClick = {},
        onFinish = {},
    )
}