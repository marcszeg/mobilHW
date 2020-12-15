package com.example.beadando.addAlbum

import android.app.Application
import androidx.lifecycle.*
import com.example.beadando.database.Albums
import com.example.beadando.database.AlbumsDatabaseDao
import kotlinx.coroutines.launch

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
            val album = Albums()
            album.title = title
            album.artist = artist
            album.release = release
            database.update(album)

            _navigateToAlbums.value = true
        }
    }
}