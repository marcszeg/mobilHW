package com.example.beadando.addAlbum

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beadando.albums.AlbumsViewModel
import com.example.beadando.database.AlbumsDatabaseDao
import java.lang.IllegalArgumentException

class AddAlbumViewModelFactory (
        private val dataSource: AlbumsDatabaseDao,
        private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAlbumViewModel::class.java)){
            return AddAlbumViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}