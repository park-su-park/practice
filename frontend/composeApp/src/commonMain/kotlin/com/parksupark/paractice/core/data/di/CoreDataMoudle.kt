package com.parksupark.paractice.core.data.di

import com.parksupark.paractice.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val sharedCoreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}
