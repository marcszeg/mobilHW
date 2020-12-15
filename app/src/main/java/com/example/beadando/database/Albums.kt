package com.example.beadando.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_table")
data class Albums(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "album_title")
    var title: String = "",
    @ColumnInfo(name = "artist_name")
    var artist: String = "",
    @ColumnInfo(name = "release_date")
    var release: String = ""
)