package com.googlebooks.ca.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookListAdapter
import com.googlebooks.ca.model.Volume
import com.googlebooks.ca.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is BookListViewModel.State.Loading -> {
                    loading.visibility = View.VISIBLE
                }

                is BookListViewModel.State.Loaded -> {
                    loading.visibility = View.GONE
                    recyclerView.adapter =
                        BookListAdapter(state.items, this@MainActivity::openDetail)
                }

                is BookListViewModel.State.Error -> {
                    loading.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(this@MainActivity, R.string.error_loading, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        })
        viewModel.loadBooks()
    }

    private fun openDetail(volume: Volume) {
        BookDetailActivity.open(this, volume)
    }
}