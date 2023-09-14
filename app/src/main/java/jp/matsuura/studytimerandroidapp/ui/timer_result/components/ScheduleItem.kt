package jp.matsuura.studytimerandroidapp.ui.timer_result.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import jp.matsuura.studytimerandroidapp.R
import jp.matsuura.studytimerandroidapp.extension.toDate
import jp.matsuura.studytimerandroidapp.extension.toTime
import java.time.OffsetDateTime

@Composable
fun ScheduleItem(
    modifier: Modifier = Modifier,
    startData: OffsetDateTime,
    endData: OffsetDateTime,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
) {
    ConstraintLayout(modifier = modifier) {
        val (icon, title, startTitle, endTitle, startDay, endDay, startTime, endTime) = createRefs()

        Image(
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 12.dp)
            },
            painter = painterResource(id = R.drawable.ic_timer),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(icon.top)
                start.linkTo(icon.end, margin = 24.dp)
                bottom.linkTo(icon.bottom)
            }, text = stringResource(id = R.string.timer_result_schedule_title), fontSize = 16.sp
        )

        Text(
            modifier = Modifier.constrainAs(startTitle) {
                top.linkTo(title.bottom, margin = 24.dp)
                start.linkTo(title.start)
            },
            text = stringResource(id = R.string.timer_result_schedule_start_title),
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(endTitle) {
                    top.linkTo(startTitle.bottom, margin = 24.dp)
                    start.linkTo(title.start)
                },
            text = stringResource(id = R.string.timer_result_schedule_end_title),
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(startDay) {
                    top.linkTo(startTitle.top)
                    start.linkTo(startTitle.end, margin = 30.dp)
                    bottom.linkTo(startTitle.bottom)
                }
                .clickable { onStartDateClick() },
            text = startData.toDate(), fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(endDay) {
                    top.linkTo(endTitle.top)
                    start.linkTo(endTitle.end, margin = 30.dp)
                    bottom.linkTo(endTitle.bottom)
                }
                .clickable { onEndDateClick() },
            text = endData.toDate(), fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(startTime) {
                    top.linkTo(startDay.top)
                    start.linkTo(startDay.end, margin = 30.dp)
                    bottom.linkTo(startDay.bottom)
                }
                .clickable { onStartTimeClick() },
            text = startData.toTime(), fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(endTime) {
                    top.linkTo(endDay.top)
                    start.linkTo(endDay.end, margin = 30.dp)
                    bottom.linkTo(endDay.bottom)
                }
                .clickable { onEndTimeClick() },
            text = endData.toTime(), fontSize = 14.sp
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ScheduleItemPreview() {
    ScheduleItem(
        modifier = Modifier.fillMaxWidth(),
        startData = OffsetDateTime.now(),
        endData = OffsetDateTime.now(),
        onStartDateClick = {},
        onEndDateClick = {},
        onStartTimeClick = {},
        onEndTimeClick = {},
    )
}