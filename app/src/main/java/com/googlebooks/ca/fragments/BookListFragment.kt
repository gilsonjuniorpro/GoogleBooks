package com.googlebooks.ca.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.googlebooks.ca.R
import com.googlebooks.ca.adapter.BookListAdapter
import com.googlebooks.ca.model.Volume
import com.googlebooks.ca.ui.BookDetailActivity
import com.googlebooks.ca.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
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

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BookListViewModel.State.Loading -> {
                    loading.visibility = View.VISIBLE
                }

                is BookListViewModel.State.Loaded -> {
                    loading.visibility = View.GONE
                    recyclerView.adapter =
                        BookListAdapter(state.items, this::openDetail)
                }

                is BookListViewModel.State.Error -> {
                    loading.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        })
        viewModel.loadBooks()
    }

    private fun openDetail(volume: Volume) {
        BookDetailActivity.open(requireContext(), volume)
    }
}
