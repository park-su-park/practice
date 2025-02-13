package com.parksupark.paractice

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.parksupark.paractice.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(
                width = 393.dp,
                height = 852.dp,
            ),
            title = "paractice",
        ) {
            App()
        }
    }
}
