package com.example.beadando.addNewAlbum

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.beadando.R
import com.example.beadando.albums.AlbumsViewModel
import com.example.beadando.albums.AlbumsViewModelFactory
import com.example.beadando.database.AlbumDatabase
import com.example.beadando.database.Albums
import kotlinx.android.synthetic.main.fragment_add_album.*
import kotlinx.android.synthetic.main.fragment_add_album.view.*


class AddNewAlbumFragment : Fragment() {

    private lateinit var mAddAlbumVewModel: AddNewAlbumViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_album, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AlbumDatabase.getInstance(application).albumsDatabaseDao

        val viewModelFactory = AddNewAlbumViewModelFactory(dataSource, application)
        mAddAlbumVewModel = ViewModelProvider(this,viewModelFactory).get(AddNewAlbumViewModel::class.java)
        //mAddAlbumVewModel = ViewModelProvider(this).get(AddNewAlbumViewModel::class.java)

        view.doneButton.setOnClickListener {
            insertAlbumToDatabase()
        }

        return view
    }

    private fun insertAlbumToDatabase() {
        val title = titleEdit.text.toString()
        val artist = artistEdit.text.toString()
        val release = releaseEdit.text.toString()   //ha int, csak torolni a '.toString()'-et

        if (checkInput(title, artist, release)) {
            val album = Albums(0,title,artist,release)  //int: Integer.parseInt(release.toString())
            mAddAlbumVewModel.addAnAlbum(album)
            findNavController().navigate(R.id.action_addNewAlbumFragment_to_albumsFragment)
        }
    }

    private fun checkInput(title: String, artist: String, release: String): Boolean {       //int: release: Editable
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(artist) && TextUtils.isEmpty(release))      //int: release.isEmpty()
    }
}