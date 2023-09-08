package jp.matsuura.studytimerandroidapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.matsuura.studytimerandroidapp.ui.theme.Purple40
import jp.matsuura.studytimerandroidapp.ui.theme.Purple80
import jp.matsuura.studytimerandroidapp.ui.theme.White

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

@Composable
fun AppIconCircleButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    iconSize: Int = 80,
    iconColor: Color = White,
    backgroundColor: Color = Purple80,
) {
    IconButton(
        modifier = modifier
            .background(shape = CircleShape, color = backgroundColor)
            .padding(20.dp),
        onClick = onClick,
        enabled = isEnabled,
    ) {
        Icon(
            modifier = Modifier.size(iconSize.dp),
            painter = painter,
            contentDescription = null,
            tint = iconColor
        )
    }
}