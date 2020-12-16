package com.example.beadando

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.beadando.databinding.FragmentTitleBinding
import timber.log.Timber

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container,false)
        binding.startButton.setOnClickListener {view: View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToAlbumsFragment())
            Timber.i("From Title to Albums")
        }
        return binding.root
    }
}