package com.parksupark.paractice.feature.auth.presentation.di

import com.parksupark.paractice.feature.auth.presentation.login.LoginViewModel
import com.parksupark.paractice.feature.auth.presentation.signup.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedAuthPresentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
}
