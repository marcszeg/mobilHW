package com.example.beadando.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlbumsDatabaseDao {
    @Insert
    suspend fun insert(album: Albums)

    @Update
    suspend fun update(album: Albums)

    @Query ("select * from albums_table where id = :key")
    suspend fun get(key: Long): Albums?

    @Query ("delete from albums_table")
    suspend fun clear()

    @Query ("select * from albums_table order by id desc")
    fun getAllAlbums(): LiveData<List<Albums>>
}