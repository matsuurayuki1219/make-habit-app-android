package jp.matsuura.studytimerandroidapp.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontSize = 14.sp,
        )
    }
}