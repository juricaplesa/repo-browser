package dev.plesa.domain

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T : Any> Result<T>.onError(action: (Exception) -> Unit) {
    if (this is Result.Error) action(exception)
}