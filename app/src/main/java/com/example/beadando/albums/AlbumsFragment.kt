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
import androidx.recyclerview.widget.GridLayoutManager
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


        binding.albumsViewModel = albumsViewModel

        val adapter = AlbumAdapter(AlbumListener { id ->
            albumsViewModel.onAlbumClicked(id)
        })
        binding.albumList.adapter = adapter //*************

        albumsViewModel.albums.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        albumsViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner, Observer { album ->
            album?.let {
                this.findNavController().navigate(AlbumsFragmentDirections.actionAlbumsFragmentToAlbumDetailFragment(album))
                albumsViewModel.onAlbumDetailNavigated()
            }
        })

        binding.setLifecycleOwner(this)

        albumsViewModel.navigateToAddNewAlbum.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(
                        AlbumsFragmentDirections.actionAlbumsFragmentToAddNewAlbumFragment()
                    )
                albumsViewModel.doneNavigating()
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

        val manager = GridLayoutManager(activity, 1)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (position) {
                0 -> 1
                else -> 1
            }
        }
        binding.albumList.layoutManager = manager

        return binding.root
    }
}