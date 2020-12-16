package com.example.beadando.addNewAlbum

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beadando.addNewAlbum.AddNewAlbumViewModel
import com.example.beadando.database.AlbumsDatabaseDao
import java.lang.IllegalArgumentException

class AddNewAlbumViewModelFactory (
    private val dataSource: AlbumsDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewAlbumViewModel::class.java)){
            return AddNewAlbumViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}