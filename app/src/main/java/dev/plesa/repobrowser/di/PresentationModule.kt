package dev.plesa.repobrowser.di

import dev.plesa.repobrowser.repository.details.RepositoryDetailsViewModel
import dev.plesa.repobrowser.repository.list.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { RepositoryListViewModel(get()) }
    viewModel { RepositoryDetailsViewModel(get()) }

}