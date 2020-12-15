package com.example.beadando.addAlbum

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.beadando.R
import com.example.beadando.database.AlbumDatabase
import com.example.beadando.databinding.FragmentAddAlbumBinding
import kotlinx.android.synthetic.main.fragment_add_album.*

class AddAlbumFragment : Fragment() {
    private val myAlbumm = MyAlbum()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_add_album)

        binding.myAlbum = myAlbumm

        binding.doneButton.setOnClickListener {
            addNewAlbum(it)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddAlbumBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_album, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AlbumDatabase.getInstance(application).albumsDatabaseDao

        val viewModelFactory = AddAlbumViewModelFactory(dataSource, application)
        val addAlbumViewModel = ViewModelProvider(this, viewModelFactory).get(AddAlbumViewModel::class.java)

        binding.setLifecycleOwner(this)
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




















    /*private fun addNewAlbum(view: View) {
        binding.apply {
            myAlbumm.artist = artistEdit.text.toString()
            myAlbumm.title = titleEdit.text.toString()
            myAlbumm.release = releaseEdit.text.toString()
            invalidateAll()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }*/
}