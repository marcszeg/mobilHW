package com.example.beadando.addAlbum

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import kotlinx.coroutines.launch
import timber.log.Timber

class AddAlbumViewModel (
        val database: AlbumsDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private val _navigateToAlbums = MutableLiveData<Boolean?>()
    val navigateToAlbums: LiveData<Boolean?>
        get() = _navigateToAlbums

    fun doneNavigating() {
        _navigateToAlbums.value = null
    }

    fun addAlbum(title: String, artist: String, release: String) {
        viewModelScope.launch {
            if (title != "" && artist != "" && release != "") {
                val album = Albums()
                database.insert(album)
                Timber.i("New album initialized")

                album.title = title
                album.artist = artist
                album.release = release
                Timber.i("Got details")

                database.update(album)
                Timber.i("DB updated")

                _navigateToAlbums.value = true
            }
        }
    }

    fun cancelAddAlbum() {
        _navigateToAlbums.value = true
    }
}