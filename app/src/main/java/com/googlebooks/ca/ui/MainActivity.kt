package com.googlebooks.ca.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)

        viewPager.adapter = BookPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.setText(
                if(position == 0){
                    R.string.tab_books
                }else{
                    R.string.tab_favorites
                }
            )
        }.attach()
    }
}