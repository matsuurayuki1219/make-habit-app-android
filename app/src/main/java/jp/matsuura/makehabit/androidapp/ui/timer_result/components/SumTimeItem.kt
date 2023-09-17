package jp.matsuura.makehabit.androidapp.ui.timer_result.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SumTimeItem(
    modifier: Modifier = Modifier,
    durationMillSec: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(text = (durationMillSec / 1000 / 60).toString(), fontSize = 64.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "分", fontSize = 32.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun SumTimeItemPreview() {
    SumTimeItem(
        modifier = Modifier.fillMaxWidth(),
        durationMillSec = 10000000,
    )
}