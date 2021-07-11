package dev.plesa.repobrowser.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.plesa.domain.model.RepositoriesSortOption
import dev.plesa.domain.onError
import dev.plesa.domain.onSuccess
import dev.plesa.domain.usecase.GetRepositoriesUseCase
import dev.plesa.repobrowser.common.base.BaseViewModel
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.model.mapToUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitHubRepositoryUI>>()
    val repositories: LiveData<List<GitHubRepositoryUI>> = _repositories

    private var lastGetRepositoriesJob: Job? = null

    fun getRepositories(query: String) {
        lastGetRepositoriesJob?.cancel()
        lastGetRepositoriesJob = viewModelScope.launch {
            getRepositoriesUseCase.getRepositories(query, RepositoriesSortOption.DEFAULT)
                .onSuccess { _repositories.value = it.map {  item -> item.mapToUI() } }
                .onError {  }
        }
    }

}