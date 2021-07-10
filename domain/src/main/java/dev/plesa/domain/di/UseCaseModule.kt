package dev.plesa.domain.di

import dev.plesa.domain.usecase.GetRepositoriesUseCase
import dev.plesa.domain.usecase.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetRepositoriesUseCase() }
    factory { GetUserUseCase() }

}