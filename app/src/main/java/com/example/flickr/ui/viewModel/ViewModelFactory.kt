package com.example.flickr.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flickr.domain.repository.FlickerRepository

class ViewModelFactory(private val repository: FlickerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(FeedsViewModel::class.java) ->
                    FeedsViewModel(repository)
                else ->
                    error("Invalid View Model class")
            }
        } as T
}