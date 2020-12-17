package com.example.beadando.albumDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beadando.database.AlbumsDatabaseDao
import java.lang.IllegalArgumentException

class AlbumDetailViewModelFactory (
    private val albumKey: Long,
    private val dataSource: AlbumsDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
            return AlbumDetailViewModel(albumKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}