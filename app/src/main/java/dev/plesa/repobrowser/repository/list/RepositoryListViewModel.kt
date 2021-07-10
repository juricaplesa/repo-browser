package dev.plesa.repobrowser.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.plesa.domain.usecase.GetRepositoriesUseCase
import dev.plesa.repobrowser.common.base.BaseViewModel
import dev.plesa.repobrowser.model.GitHubRepositoryUI

class RepositoryListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitHubRepositoryUI>>()
    val repositories: LiveData<List<GitHubRepositoryUI>> = _repositories

    fun getRepositories() {

    }

}