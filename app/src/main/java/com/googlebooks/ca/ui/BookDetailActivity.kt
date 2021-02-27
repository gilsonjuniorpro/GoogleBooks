package com.googlebooks.ca.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.googlebooks.ca.R
import com.googlebooks.ca.model.Volume
import com.googlebooks.ca.repository.BookRepository
import com.googlebooks.ca.util.Dominios
import com.googlebooks.ca.viewmodel.BookDetailViewModel
import com.googlebooks.ca.viewmodel.BookViewModelFactory
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    private val viewModel: BookDetailViewModel by lazy {
        ViewModelProvider(
            this,
            BookViewModelFactory(
                BookRepository(this)
            )
        ).get(BookDetailViewModel::class.java)
    }

    lateinit var volume: Volume

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val volume = intent.getParcelableExtra<Volume>(EXTRA_BOOK)

        if(volume != null){
            this.volume = volume
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

            viewModel.isFavorite.observe(
                this,
                Observer { isFavorite ->
                    if(isFavorite) {
                        fab.setImageResource(R.drawable.ic_delete)
                        fab.setOnClickListener{
                            viewModel.removeFromFavorites(volume)
                        }
                    } else {
                        fab.setImageResource(R.drawable.ic_add)
                        fab.setOnClickListener{
                            viewModel.saveToFavorites(volume)
                        }
                    }

                }
            )
            viewModel.onCreate(volume)
        }else{
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_view_web){
            startActivity(Intent(
                Intent.ACTION_VIEW, Uri.parse(Dominios.GOOGLE_BOOKS + volume.id)
            ))
        }
        return super.onOptionsItemSelected(item)
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
