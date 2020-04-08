package com.googlebooks.ca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.googlebooks.ca.R
import com.googlebooks.ca.model.Volume
import kotlinx.android.synthetic.main.item_book.view.*

class BookListAdapter(
    val items: List<Volume>
) : RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val volume = items[position]
        holder.tvTitle.text = volume.volumeInfo.title
        holder.tvAuthors.text = volume.volumeInfo.authors?.joinToString() ?: ""
        holder.tvPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
        holder.ivThumbnail?.load(volume.volumeInfo.imageLinks?.thumbnail) {
            crossfade(true)
            crossfade(500)
            placeholder(R.drawable.image_not_found)
        }
    }

    class BookHolder(rootView: View): RecyclerView.ViewHolder(rootView){
        val ivThumbnail: ImageView = rootView.ivThumbnail
        val tvTitle: TextView = rootView.tvTitle
        val tvAuthors: TextView = rootView.tvAuthors
        val tvPages: TextView = rootView.tvPages
    }
}