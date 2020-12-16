package com.example.beadando.database

import androidx.lifecycle.LiveData

class AlbumRepository(private val albumDao: AlbumsDatabaseDao) {

    val getAllAlbums: LiveData<List<Albums>> = albumDao.getAllAlbums()

    suspend fun addANewAlbum(album: Albums) {
        albumDao.insert(album)
    }
}