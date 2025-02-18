package com.parksupark.paractice.feature.todo.presentation.di

import com.parksupark.paractice.feature.todo.presentation.home.HomeViewModel
import com.parksupark.paractice.feature.todo.presentation.write.WriteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedTodoPresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::WriteViewModel)
}
