package com.example.beadando.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.beadando.R
import com.example.beadando.database.AlbumDatabase
import androidx.lifecycle.Observer
import com.example.beadando.databinding.FragmentAlbumsBinding
import com.google.android.material.snackbar.Snackbar

class AlbumsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAlbumsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_albums, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AlbumDatabase.getInstance(application).albumsDatabaseDao

        val viewModelFactory = AlbumsViewModelFactory(dataSource, application)
        val albumsViewModel = ViewModelProvider(this,viewModelFactory).get(AlbumsViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.albumsViewModel = albumsViewModel

        albumsViewModel.navigateToAddAlbum.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(
                        AlbumsFragmentDirections.actionAlbumsFragmentToAddAlbumFragment()
                    )
            }
        })

        albumsViewModel.showSnackbarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                albumsViewModel.doneShowingSnackbar()
            }
        })
        return binding.root
    }
}