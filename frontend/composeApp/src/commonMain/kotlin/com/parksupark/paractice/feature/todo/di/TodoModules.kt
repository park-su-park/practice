package com.parksupark.paractice.feature.todo.di

import com.parksupark.paractice.feature.todo.presentation.di.sharedTodoPresentationModule
import org.koin.dsl.module

val todoModules = module {
    includes(
        sharedTodoPresentationModule,
    )
}
