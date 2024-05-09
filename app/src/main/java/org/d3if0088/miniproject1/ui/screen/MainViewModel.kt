package org.d3if0088.miniproject1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0088.miniproject1.database.ComicsDao
import org.d3if0088.miniproject1.model.Comics

class MainViewModel(dao: ComicsDao): ViewModel() {

    val data: StateFlow<List<Comics>> = dao.getComics().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}