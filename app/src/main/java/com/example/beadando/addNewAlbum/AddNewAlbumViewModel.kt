package com.example.beadando.addNewAlbum

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.beadando.database.AlbumDatabase
import com.example.beadando.database.AlbumRepository
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewAlbumViewModel(
        val database: AlbumsDatabaseDao,
        application: Application): AndroidViewModel(application) {

    private val readAllAlbum: LiveData<List<Albums>>
    private val repository: AlbumRepository

    init {
        //val albumDao = AlbumDatabase.getInstance(application).albumsDBDao()
        repository = AlbumRepository(database)
        readAllAlbum = repository.getAllAlbums
    }

    fun addAnAlbum(album: Albums) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addANewAlbum(album)
        }
    }
}