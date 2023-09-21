package jp.matsuura.makehabit.androidapp.ui.common

import android.app.TimePickerDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import jp.matsuura.makehabit.androidapp.R
import jp.matsuura.makehabit.androidapp.model.TimeModel
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTimePicker(
    modifier: Modifier = Modifier,
    currentTime: Long?,
    onUpdateTime: (TimeModel) -> Unit,
    onDismiss: () -> Unit,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = 1,
        initialMinute = 1,
    )

    TimePickerDialog(
        modifier = modifier,
        onCancel = onDismiss,
        onConfirm = {
            onUpdateTime(
                TimeModel(
                    hour = timePickerState.hour,
                    minute = timePickerState.minute,
                )
            )
        },
    ) {
        TimePicker(state = timePickerState)
    }
}