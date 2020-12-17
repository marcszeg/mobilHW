package com.example.beadando.albums

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import com.example.beadando.formatAlbums
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumsViewModel (
        val dataSource: AlbumsDatabaseDao,
        application: Application) : ViewModel() {

    val database = dataSource

    //private var newAlbum = MutableLiveData<Albums>()
    val albums = database.getAllAlbums()

    val albumsString = Transformations.map(albums) { albums ->
        formatAlbums(albums, application.resources)
    }

    private val _navigateToAlbumDetail = MutableLiveData<Long>()
    val navigateToAlbumDetail: LiveData<Long>
        get() = _navigateToAlbumDetail

    fun onAlbumClicked(id: Long) {
        _navigateToAlbumDetail.value = id
    }

    fun onAlbumDetailNavigated() {
        _navigateToAlbumDetail.value = null
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

    fun doneNavigating() {
        _navigateToAddNewAlbum.value = null
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            //newAlbum.value = null
        }
        _showSnackbarEvent.value = true
    }

    suspend fun clear() {
        database.clear()
        Timber.i("DB cleared")
    }
}