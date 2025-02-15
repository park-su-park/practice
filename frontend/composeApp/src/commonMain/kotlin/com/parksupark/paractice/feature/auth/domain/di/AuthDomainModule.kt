package com.parksupark.paractice.feature.auth.domain.di

import com.parksupark.paractice.feature.auth.domain.usecase.LoginUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedAuthDomainModule = module {
    singleOf(::LoginUseCase)
}
