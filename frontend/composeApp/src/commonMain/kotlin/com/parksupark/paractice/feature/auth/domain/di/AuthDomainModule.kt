package com.parksupark.paractice.feature.auth.domain.di

import com.parksupark.paractice.feature.auth.domain.usecase.LoginUseCase
import com.parksupark.paractice.feature.auth.domain.usecase.SignupUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedAuthDomainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::SignupUseCase)
}
