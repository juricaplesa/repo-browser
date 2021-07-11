package dev.plesa.repobrowser.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.plesa.repobrowser.common.Event

open class BaseViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    @Suppress("PropertyName")
    protected val _openExternalUrl = MutableLiveData< Event<String>>()
    val openExternalUrl: LiveData<Event<String>> = _openExternalUrl

}