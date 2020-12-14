package com.example.beadando.addAlbum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.beadando.R
import com.example.beadando.database.AlbumDatabase
import com.example.beadando.databinding.FragmentAddAlbumBinding

class AddAlbumFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddAlbumBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_album, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = AddAlbumFragmentArgs.fromBundle(requireArguments())
        val dataSource = AlbumDatabase.getInstance(application).albumsDatabaseDao
        val viewModelFactory = AddAlbumViewModelFactory(arguments.addAlbumKey, dataSource)
        val addAlbumViewModel = ViewModelProvider(this, viewModelFactory).get(AddAlbumViewModel::class.java)

        binding.addAlbumViewModel = addAlbumViewModel
        addAlbumViewModel.navigateToAlbums.observe(this, Observer {
            if (it == true){
                this.findNavController().navigate(
                    AddAlbumFragmentDirections.actionAddAlbumFragmentToAlbumsFragment()
                )
                addAlbumViewModel.doneNavigating()
            }
        })
        return binding.root
    }


}