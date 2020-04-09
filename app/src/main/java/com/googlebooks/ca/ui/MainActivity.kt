package com.googlebooks.ca.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookListAdapter
import com.googlebooks.ca.model.BookHttp
import com.googlebooks.ca.model.Volume
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch{
            val result = withContext(Dispatchers.IO) {
                BookHttp.searchBook("Dominando o Android")
            }

            result?.items?.let{
                recyclerView.adapter = BookListAdapter(it) { volume ->
                    openDetail(volume)
                }
            }

        }
    }

    private fun openDetail(volume: Volume){
        BookDetailActivity.open(this, volume)
    }
}