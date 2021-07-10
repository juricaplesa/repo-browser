package dev.plesa.data.di

import dev.plesa.data.RepositoryImpl
import dev.plesa.domain.Repository
import org.koin.dsl.module

val repositoryModule = module {

    factory<Repository> {
        RepositoryImpl(get())
    }

}