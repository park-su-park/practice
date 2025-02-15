package com.parksupark.paractice.di

import com.parksupark.paractice.core.di.coreModules
import com.parksupark.paractice.feature.auth.di.authModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(coreModules)
        modules(authModules)
    }
}
