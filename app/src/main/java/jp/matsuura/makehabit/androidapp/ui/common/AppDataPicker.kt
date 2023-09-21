package jp.matsuura.makehabit.androidapp.ui.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import jp.matsuura.makehabit.androidapp.R
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDataPicker(
    modifier: Modifier = Modifier,
    currentDate: Long?,
    onUpdateDate: (Long?) -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = currentDate ?: Instant.now().toEpochMilli()
    )
    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onUpdateDate(datePickerState.selectedDateMillis)
                }
            ) {
                Text(text = stringResource(id = R.string.common_ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.common_cancel))
            }
        },
        modifier = modifier,
    ) {
        DatePicker(state = datePickerState)
    }

}