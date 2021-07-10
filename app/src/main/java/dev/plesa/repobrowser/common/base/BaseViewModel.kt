package dev.plesa.repobrowser.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int> = _errorMessage

    protected val _openExternalUrl = MutableLiveData<String>()
    val openExternalUrl: LiveData<String> = _openExternalUrl

}