package com.example.beadando.albums

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beadando.database.AlbumsDatabaseDao
import java.lang.IllegalArgumentException
import javax.sql.CommonDataSource

class AlbumsViewModelFactory (
    private val dataSource: AlbumsDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)){
            return AlbumsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException ("Unknown ViewModel Class")
    }
}
