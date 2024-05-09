package org.d3if0088.miniproject1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0088.miniproject1.database.ComicsDao
import org.d3if0088.miniproject1.model.Comics
import java.text.SimpleDateFormat
import java.util.Locale

class DetailViewModel(private val dao: ComicsDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(judul: String, author: String, released: String, genre: String) {
        val comics = Comics(
            title = judul,
            author = author,
            released = released,
            genre = genre
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(comics)
        }
    }

    suspend fun getComics(id: Long): Comics? {
        return dao.getComicsById(id)
    }

    fun update(id: Long, judul: String, author: String, released: String, genre: String) {
        val comics = Comics(
            id = id,
            title = judul,
            author = author,
            released = released,
            genre = genre
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