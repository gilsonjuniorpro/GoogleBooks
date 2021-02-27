package com.googlebooks.ca.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.googlebooks.ca.fragments.BookFavoritesFragment
import com.googlebooks.ca.fragments.BookListFragment

class BookPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) BookListFragment() else BookFavoritesFragment()
    }
}