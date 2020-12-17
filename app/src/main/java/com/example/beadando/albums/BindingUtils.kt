package com.example.beadando.albums

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.beadando.database.Albums

@BindingAdapter("albumTitleString")
fun TextView.setAlbumTitleString(item: Albums?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("albumArtistString")
fun TextView.setAlbumArtistString(item: Albums?) {
    item?.let {
        text = item.artist
    }
}

@BindingAdapter("albumReleaseString")
fun TextView.setAlbumReleaseString(item: Albums?) {
    item?.let {
        text = item.release
    }
}

