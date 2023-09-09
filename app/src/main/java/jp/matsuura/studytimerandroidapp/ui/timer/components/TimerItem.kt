package jp.matsuura.studytimerandroidapp.ui.timer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.matsuura.studytimerandroidapp.ui.theme.Pink40
import jp.matsuura.studytimerandroidapp.ui.theme.Pink80

private const val PROGRESS_WIDTH = 24f
private val progressColor = Pink80
private val trackColor = Pink40

@Composable
fun TimerItem(
    modifier: Modifier = Modifier,
    hours: Int,
    minutes: Int,
    seconds: Int,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.size(240.dp),
            onDraw = {

                // 外枠を描画
                drawArc(
                    color = Color.Black,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = PROGRESS_WIDTH, cap = StrokeCap.Round),
                    size = Size(size.width, size.height)
                )

                // 背景を描画
                drawArc(
                    color = progressColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = PROGRESS_WIDTH, cap = StrokeCap.Round),
                    size = Size(size.width, size.height)
                )

                // 進捗を描画
                drawArc(
                    color = trackColor,
                    startAngle = 270f,
                    sweepAngle = 150f,
                    useCenter = false,
                    style = Stroke(width = PROGRESS_WIDTH, cap = StrokeCap.Round),
                    size = Size(size.width, size.height)
                )

            }
        )
        Text(
            text = getTimerText(
                hours, minutes, seconds
            ), fontSize = 48.sp, fontWeight = FontWeight.Bold
        )
    }
}

private fun getTimerText(
    hours: Int,
    minutes: Int,
    seconds: Int,
): String {
    return if (hours == 0 && minutes == 0) {
        "%02d".format(seconds)
    } else if (hours == 0) {
        "%02d:%02d".format(minutes, seconds)
    } else {
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}

@Preview(showBackground = true)
@Composable
private fun HourTimerItemPreviews() {
    TimerItem(
        modifier = Modifier,
        hours = 1,
        minutes = 10,
        seconds = 40,
    )
}

@Preview(showBackground = true)
@Composable
private fun MinuteTimerItemPreviews() {
    TimerItem(
        modifier = Modifier,
        hours = 0,
        minutes = 10,
        seconds = 40,
    )
}

@Preview(showBackground = true)
@Composable
private fun SecondTimerItemPreviews() {
    TimerItem(
        modifier = Modifier,
        hours = 0,
        minutes = 0,
        seconds = 40,
    )
}