package jp.matsuura.makehabit.androidapp.ui.category_selection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import jp.matsuura.makehabit.androidapp.ui.theme.Purple80
import java.time.Instant

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryModel,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = if (isSelected) Purple80 else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(
                vertical = 20.dp,
                horizontal = 12.dp,
            ),
    ) {
        Text(
            text = category.categoryName,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectedCategoryItemPreviews() {
    CategoryItem(
        modifier = Modifier.fillMaxWidth(),
        category = CategoryModel(
            id = 1,
            categoryName = "ランニング",
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        ),
        isSelected = true,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun UnSelectedCategoryItemPreviews() {
    CategoryItem(
        modifier = Modifier.fillMaxWidth(),
        category = CategoryModel(
            id = 1,
            categoryName = "ランニング",
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        ),
        isSelected = false,
        onClick = {},
    )
}