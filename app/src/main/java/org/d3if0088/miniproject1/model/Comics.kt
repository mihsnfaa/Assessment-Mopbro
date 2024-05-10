package org.d3if0088.miniproject1.model

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class Comics(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val desc: String,
    val author: String,
    val released: String,
    val genre: String,
    @StringRes val status: Int
)
