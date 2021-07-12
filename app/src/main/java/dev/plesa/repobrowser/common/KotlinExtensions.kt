package dev.plesa.repobrowser.common

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}

fun Date.domainDateToUI(): String {
    val outputPattern = "dd.MM.yyyy, HH:mm"
    return try {
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        outputFormat.format(this)
    } catch (exception: Exception) {
        ""
    }
}