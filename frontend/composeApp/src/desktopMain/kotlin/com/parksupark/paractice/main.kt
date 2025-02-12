package com.parksupark.paractice

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() =
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
