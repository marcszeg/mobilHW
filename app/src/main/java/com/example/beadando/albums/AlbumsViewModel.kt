package com.example.beadando.albums

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import com.example.beadando.formatAlbums
import kotlinx.coroutines.launch

class AlbumsViewModel (
    val database: AlbumsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private val albums = database.getAllAlbums()

    val albumsString = Transformations.map(albums) { albums ->
        formatAlbums(albums, application.resources)
    }

    private val _navigateToAddAlbum = MutableLiveData<Albums>()
    val navigateToAddAlbum: LiveData<Albums>
        get() = _navigateToAddAlbum

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

    /*fun onClear() {
        ViewModelScope.launch {
            clear()
        }
        _showSnackbarEvent.value = true
    }*/

    suspend fun clear() {
        database.clear()
        _showSnackbarEvent.value = true
    }
}