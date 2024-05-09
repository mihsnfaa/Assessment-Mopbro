package org.d3if0088.miniproject1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if0088.miniproject1.model.Comics

@Database(entities = [Comics::class], version = 1, exportSchema = false)
abstract class ComicsDb : RoomDatabase() {

    abstract val dao: ComicsDao

    companion object {

        @Volatile
        private var INSTANCE: ComicsDb? = null

        fun getInstance(context: Context): ComicsDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ComicsDb::class.java,
                        "catatan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}