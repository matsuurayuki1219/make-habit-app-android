package jp.matsuura.studytimerandroidapp.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.matsuura.studytimerandroidapp.extension.toDayOfWeek
import jp.matsuura.studytimerandroidapp.extension.toOffsetDateTime
import jp.matsuura.studytimerandroidapp.extension.toTime
import jp.matsuura.studytimerandroidapp.model.TransactionDetailModel
import jp.matsuura.studytimerandroidapp.ui.theme.Purple40
import java.time.Instant
import java.time.LocalDate

@Composable
fun TransactionHeaderItem(
    modifier: Modifier = Modifier,
    date: LocalDate,
) {
    Text(
        modifier = modifier,
        text = date.year.toString() + "/" + date.monthValue.toString() + "/" + date.dayOfMonth + " (" + date.toDayOfWeek() + ")",
        fontSize = 16.sp,
    )
}

@Composable
fun TransactionSectionItem(
    modifier: Modifier = Modifier,
    transaction: TransactionDetailModel,
    onClick: (TransactionDetailModel) -> Unit,
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = Purple40, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(transaction) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${
                        transaction.startedAt.toOffsetDateTime().toTime()
                    } - ${transaction.endedAt.toOffsetDateTime().toTime()}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "${transaction.durationSec} min", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = transaction.categoryName, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionHeaderItemPreview() {
    TransactionHeaderItem(
        date = LocalDate.now(),
    )
}

@Preview(showBackground = true)
@Composable
private fun TransactionSectionItemPreview() {
    TransactionSectionItem(
        modifier = Modifier.fillMaxWidth(),
        transaction = TransactionDetailModel(
            transactionId = 1,
            categoryId = 1,
            categoryName = "ランニング",
            startedAt = Instant.now(),
            endedAt = Instant.now(),
            durationMillSec = 1000,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        ),
        onClick = {},
    )
}