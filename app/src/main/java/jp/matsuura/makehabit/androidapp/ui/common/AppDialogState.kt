package jp.matsuura.makehabit.androidapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class AppDialogState <T> {

    var isShow by mutableStateOf(false)
        private set

    var type by mutableStateOf<T?>(null)
        private set

    fun show(type: T) {
        this.type = type
        isShow = true
    }

    fun dismiss() {
        isShow = false
        type = null
    }
}

@Composable
fun <T> rememberAppDialogState(): AppDialogState <T> {
    return remember { AppDialogState() }
}