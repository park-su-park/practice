package com.parksupark.paractice.feature.auth.data.di

import com.parksupark.paractice.feature.auth.data.AuthRepositoryImpl
import com.parksupark.paractice.feature.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedAuthDataModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}
