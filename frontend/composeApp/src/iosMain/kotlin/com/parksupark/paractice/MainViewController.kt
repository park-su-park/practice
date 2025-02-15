package com.parksupark.paractice

import androidx.compose.ui.window.ComposeUIViewController
import com.parksupark.paractice.di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        },
    ) {
        App()
    }
