package com.googlebooks.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookListAdapter
import com.googlebooks.ca.model.BookHttp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        object: Thread(){
            override fun run() {
                super.run()
                val result = BookHttp.searchBook("Dominando o Android")

                runOnUiThread{
                    result?.items?.let{
                        recyclerView.adapter = BookListAdapter(it)
                    }
                }
            }
        }.start()
    }
}
