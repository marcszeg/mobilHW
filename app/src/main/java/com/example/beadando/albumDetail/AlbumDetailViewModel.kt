package com.example.beadando.albumDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao

class AlbumDetailViewModel(
    private val albumKey: Long = 0L,
    dataSource: AlbumsDatabaseDao) : ViewModel() {

    val database = dataSource

    private val album: LiveData<Albums>

    fun getAlbum() = album

    init {
        album = database.getAlbumWithId(albumKey)
    }

    private val _navigateToAlbums = MutableLiveData<Boolean?>()

    val navigateToAlbums: LiveData<Boolean?>
        get() = _navigateToAlbums

    fun doneNavigating() {
        _navigateToAlbums.value = null
    }

    fun onBack() {
        _navigateToAlbums.value = true
    }
}