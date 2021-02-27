package com.googlebooks.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.googlebooks.ca.repository.BookRepository

class BookFavoritesViewModel(
    repository: BookRepository
) : ViewModel() {
    val favoriteBooks = repository.allFavorites().asLiveData()
}