package com.googlebooks.ca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookListAdapter
import com.googlebooks.ca.model.Volume
import com.googlebooks.ca.repository.BookRepository
import com.googlebooks.ca.ui.BookDetailActivity
import com.googlebooks.ca.viewmodel.BookFavoritesViewModel
import com.googlebooks.ca.viewmodel.BookViewModelFactory
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookFavoritesFragment : Fragment() {

    private val viewModel: BookFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            BookViewModelFactory(
                BookRepository(requireContext())
            )
        ).get(BookFavoritesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.visibility = View.GONE

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )

        viewModel.favoriteBooks.observe(viewLifecycleOwner, Observer { items ->
            loading.visibility = View.GONE
            recyclerView.adapter = BookListAdapter(items, this::openDetail)
        })
    }

    private fun openDetail(volume: Volume) {
        BookDetailActivity.open(requireContext(), volume)
    }

}
