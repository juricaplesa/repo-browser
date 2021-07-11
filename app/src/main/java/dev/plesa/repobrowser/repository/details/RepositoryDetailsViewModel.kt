package dev.plesa.repobrowser.repository.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.plesa.domain.onError
import dev.plesa.domain.onSuccess
import dev.plesa.domain.usecase.GetUserUseCase
import dev.plesa.repobrowser.common.Event
import dev.plesa.repobrowser.common.base.BaseViewModel
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.model.GitHubUserUI
import dev.plesa.repobrowser.model.mapToUI
import kotlinx.coroutines.launch

class RepositoryDetailsViewModel(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<GitHubUserUI>()
    val user: LiveData<GitHubUserUI> = _user

    fun getUser(url: String) {
        viewModelScope.launch {
            getUserUseCase.getUser(url)
                .onSuccess { _user.value = it.mapToUI() }
                .onError {  }
        }
    }

    fun onRepositoryButtonClicked(repository: GitHubRepositoryUI) {
        _openExternalUrl.value = Event(repository.htmlUrl)
    }

    fun onUserButtonClicked(repository: GitHubRepositoryUI) {
        _openExternalUrl.value = Event(repository.user.htmlUrl)
    }

}