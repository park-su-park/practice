package com.parksupark.paractice.feature.auth.di

import com.parksupark.paractice.feature.auth.data.di.sharedAuthDataModule
import com.parksupark.paractice.feature.auth.domain.di.sharedAuthDomainModule
import com.parksupark.paractice.feature.auth.presentation.di.sharedAuthPresentationModule

val authModules = listOf(
    sharedAuthDataModule,
    sharedAuthDomainModule,
    sharedAuthPresentationModule,
)
