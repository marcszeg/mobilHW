package com.example.beadando.albums

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import com.example.beadando.formatAlbums
import kotlinx.coroutines.launch

class AlbumsViewModel (
        val database: AlbumsDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    private var newAlbum = MutableLiveData<Albums>()
    private val albums = database.getAllAlbums()

    val albumsString = Transformations.map(albums) { albums ->
        formatAlbums(albums, application.resources)
    }


    private val _navigateToAddAlbum = MutableLiveData<Albums>()
    val navigateToAddAlbum: LiveData<Albums>
        get() = _navigateToAddAlbum

    init {
        initializeAlbum()
    }

    private fun initializeAlbum() {
        viewModelScope.launch {
            newAlbum.value = getAlbumFromDB()
            //newAlbum.value = database.getNewAlbum()
        }
    }

    private fun getAlbumFromDB(): Albums? {
        var album = database.getNewAlbum()
        return album
    }

    fun addANewAlbum() {
        viewModelScope.launch {
            val aNewAlbum = Albums()
            insert(aNewAlbum)
            newAlbum.value = getAlbumFromDB()

            val aalbum = newAlbum.value ?: return@launch
            update(aNewAlbum)
            _navigateToAddAlbum.value = aalbum
        }
    }

    private suspend fun insert(album: Albums) {
        database.insert(album)
    }

    private suspend fun update(album: Albums) {
        database.update(album)
    }

    val clearButtonVisible = Transformations.map(albums) {
        it?.isNotEmpty()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            newAlbum.value = null
        }
        _showSnackbarEvent.value = true
    }

    suspend fun clear() {
        database.clear()
    }
}