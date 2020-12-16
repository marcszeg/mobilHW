package com.example.beadando

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.beadando.database.Albums
import java.lang.StringBuilder

fun convertIntToString(num: Int): String {
    return num.toString()
}


fun formatAlbums(albums: List<Albums>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.title))
        albums.forEach{
            append("<br>")
            append("<b>Artist:</b>")
            append("\t${it.artist}<br>")
            append("<b>Title:</b>")
            append("\t${it.title}<br>")
            append("<b>Release year:</b>")
            //append("\t${convertIntToString(it.release)}<br>")
            append("\t${it.release}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}