package com.example.beadando.addAlbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beadando.database.AlbumsDatabaseDao
import kotlinx.coroutines.launch

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

    fun addAlbum(title: String, artist: String, release: String) {
        viewModelScope.launch {
            val album = database.get(addAlbumKey) ?:return@launch
            album.title = title
            album.artist = artist
            album.release = release
            database.update(album)

            _navigateToAlbums.value = true
        }
    }
}