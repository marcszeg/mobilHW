package com.example.beadando.albums

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import com.example.beadando.formatAlbums
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumsViewModel (
        val database: AlbumsDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var newAlbum = MutableLiveData<Albums>()
    private val albums = database.getAllAlbums()

    val albumsString = Transformations.map(albums) { albums ->
        formatAlbums(albums, application.resources)
    }


    private val _navigateToAddNewAlbum = MutableLiveData<Boolean?>()
    val navigateToAddNewAlbum: LiveData<Boolean?>
        get() = _navigateToAddNewAlbum

    val clearButtonVisible = Transformations.map(albums) {
        it?.isNotEmpty()
    }

    fun addANewAlbum() {
        _navigateToAddNewAlbum.value = true
        Timber.i("From Albums to AddAlbum")
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
        Timber.i("DB cleared")
    }
}