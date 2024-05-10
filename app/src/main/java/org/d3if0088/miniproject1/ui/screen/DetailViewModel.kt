package org.d3if0088.miniproject1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0088.miniproject1.database.ComicsDao
import org.d3if0088.miniproject1.model.Comics

class DetailViewModel(private val dao: ComicsDao) : ViewModel() {

    fun insert(title: String, desc: String, author: String, released: String, genre: String, status: Int) {
        val comics = Comics(
            title = title,
            desc = desc,
            author = author,
            released = released,
            genre = genre,
            status = status
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(comics)
        }
    }

    suspend fun getComics(id: Long): Comics? {
        return dao.getComicsById(id)
    }

    fun update(id: Long, title: String,desc: String, author: String, released: String, genre: String, status: Int) {
        val comics = Comics(
            id = id,
            title = title,
            desc = desc,
            author = author,
            released = released,
            genre = genre,
            status = status
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(comics)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}