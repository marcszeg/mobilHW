package com.example.beadando.albumDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beadando.R
import com.example.beadando.database.AlbumDatabase
import com.example.beadando.databinding.FragmentAlbumDetailBinding

class AlbumDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentAlbumDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_album_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments: AlbumDetailFragmentArgs by navArgs() //.fromBundle(arguments)

        val dataSource = AlbumDatabase.getInstance(application).albumsDatabaseDao
        val viewModelFactory = AlbumDetailViewModelFactory(arguments.albumKey, dataSource)

        val albumDetailViewModel = ViewModelProvider (
            this, viewModelFactory).get(AlbumDetailViewModel::class.java)

        binding.albumDetailViewModel = albumDetailViewModel

        binding.setLifecycleOwner(this)

        albumDetailViewModel.navigateToAlbums.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    AlbumDetailFragmentDirections.actionAlbumDetailFragmentToAlbumsFragment())
                albumDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}