package dev.plesa.repobrowser.repository.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.plesa.domain.common.IncompleteDataException
import dev.plesa.domain.common.MaximumRequestsException
import dev.plesa.domain.common.NextRepositoriesNotFoundException
import dev.plesa.domain.model.RepositoriesSortOption
import dev.plesa.domain.onError
import dev.plesa.domain.onSuccess
import dev.plesa.domain.usecase.GetRepositoriesUseCase
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.Event
import dev.plesa.repobrowser.common.base.BaseViewModel
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.model.mapToUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _repositories = MutableLiveData<List<GitHubRepositoryUI>>()
    val repositories: LiveData<List<GitHubRepositoryUI>> = _repositories

    private val _nextRepositories = MutableLiveData<List<GitHubRepositoryUI>>()
    val nextRepositories: LiveData<List<GitHubRepositoryUI>> = _nextRepositories

    private val _showDetails = MutableLiveData<Event<GitHubRepositoryUI>>()
    val showDetails: LiveData<Event<GitHubRepositoryUI>> = _showDetails

    private var selectedSortOption = RepositoriesSortOption.DEFAULT
    private var lastGetRepositoriesJob: Job? = null
    private var hasMoreData = true

    fun onQueryChanged(query: String) {
        getRepositories(query)
    }

    fun onSelectedSortChanged(query: String, selectedSort: RepositoriesSortOption) {
        selectedSortOption = selectedSort
        if (query.isNotBlank()) {
            getRepositories(query)
        }
    }

    private fun getRepositories(query: String) {
        lastGetRepositoriesJob?.cancel()
        lastGetRepositoriesJob = viewModelScope.launch {
            _dataLoading.value = true
            hasMoreData = true
            getRepositoriesUseCase.getRepositories(query, selectedSortOption)
                .onSuccess { _repositories.value = it.map { item -> item.mapToUI() } }
                .onError { exception ->
                    when (exception) {
                        is IncompleteDataException -> {
                            _errorMessage.value = Event(R.string.error_incomplete)
                            hasMoreData = false
                        }
                        is MaximumRequestsException ->
                            _errorMessage.value = Event(R.string.error_maximum_requests)
                        else -> _errorMessage.value = Event(R.string.error_general)
                    }
                }
            _dataLoading.value = false
        }
    }

    fun onScrolledNearEnd() {
        if (lastGetRepositoriesJob == null || lastGetRepositoriesJob?.isCompleted != false && hasMoreData) {
            getNextRepositories()
        }
    }

    private fun getNextRepositories() {
        lastGetRepositoriesJob = viewModelScope.launch {
            _dataLoading.value = true
            getRepositoriesUseCase.getNextRepositories()
                .onSuccess { _nextRepositories.value = it.map { item -> item.mapToUI() } }
                .onError { exception ->
                    when (exception) {
                        is IncompleteDataException -> {
                            _errorMessage.value = Event(R.string.error_incomplete)
                            hasMoreData = false
                        }
                        is NextRepositoriesNotFoundException -> hasMoreData = false
                        is MaximumRequestsException ->
                            _errorMessage.value = Event(R.string.error_maximum_requests)
                        else -> _errorMessage.value = Event(R.string.error_general)
                    }
                }
            _dataLoading.value = false
        }
    }

    fun onItemClicked(repository: GitHubRepositoryUI) {
        _showDetails.value = Event(repository)
    }

    fun onImageClicked(repository: GitHubRepositoryUI) {
        _openExternalUrl.value = Event(repository.user.htmlUrl)
    }

}