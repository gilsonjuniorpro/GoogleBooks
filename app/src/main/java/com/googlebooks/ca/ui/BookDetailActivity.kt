package com.googlebooks.ca.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import com.googlebooks.ca.R
import com.googlebooks.ca.model.Volume
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val volume = intent.getParcelableExtra<Volume>(EXTRA_BOOK)

        if(volume != null){
            tvTitle.text = volume.volumeInfo.title
            tvAuthors.text = volume.volumeInfo.authors?.joinToString() ?: ""
            tvPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
            tvPublisher.text = volume.volumeInfo.publisher
            tvPublishedDate.text = volume.volumeInfo.publishedDate
            tvDescription.text = volume.volumeInfo.description

            if(volume.volumeInfo.imageLinks != null) {
                ivThumbnail?.load(volume.volumeInfo.imageLinks?.thumbnail) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.image_not_found)
                }
            }else{
                ivThumbnail.setImageResource(R.drawable.image_not_found)
            }
        }else{
            finish()
        }
    }

    companion object{
        private const val EXTRA_BOOK = "book"

        fun open(context: Context, volume: Volume){
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK, volume)
            context.startActivity(intent)
        }
    }
}
