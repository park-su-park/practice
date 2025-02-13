package com.parksupark.paractice.feature.auth.di

import com.parksupark.paractice.feature.auth.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::LoginViewModel)
}
