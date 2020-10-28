package com.googlebooks.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.googlebooks.ca.repository.BookRepository
import java.lang.IllegalArgumentException

class BookViewModelFactory(val repository: BookRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookFavoritesViewModel::class.java)){
            return BookFavoritesViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(BookDetailViewModel::class.java)){
            return BookDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}