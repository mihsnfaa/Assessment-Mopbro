package org.d3if0088.miniproject1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0088.miniproject1.model.Comics

@Dao
interface ComicsDao {

    @Insert
    suspend fun insert(comics: Comics)

    @Update
    suspend fun update(comics: Comics)

    @Query("SELECT * FROM comics ORDER BY released DESC")
    fun getComics(): Flow<List<Comics>>

    @Query("SELECT * FROM comics WHERE id = :id")
    suspend fun getComicsById(id: Long): Comics?

    @Query("DELETE FROM comics WHERE id = :id")
    suspend fun deleteById(id: Long)
}