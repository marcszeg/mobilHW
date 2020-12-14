package com.example.beadando.addAlbum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beadando.database.AlbumsDatabaseDao
import java.lang.IllegalArgumentException

class AddAlbumViewModelFactory (
    private val addAlbumKey: Long,
    private val dataSource: AlbumsDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAlbumViewModel::class.java)){
            return AddAlbumViewModelFactory(addAlbumKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}