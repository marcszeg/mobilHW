package com.example.beadando.addAlbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beadando.database.AlbumsDatabaseDao

class AddAlbumViewModel (
    private val addAlbumKey: Long = 0L,
    val database: AlbumsDatabaseDao
) : ViewModel() {
    private val _navigateToAlbums = MutableLiveData<Boolean?>()
    val navigateToAlbums: LiveData<Boolean?>
        get() = _navigateToAlbums

    fun doneNavigating() {
        _navigateToAlbums.value = null
    }


}