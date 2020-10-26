package com.jimenard.scrummerstest.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ViewModel Factory personalizado que retorna el view model necesario por modulo
 */
@Singleton
class ScrummersViewModelFactory<VM : ViewModel>
@Inject
constructor(private val viewModel: Lazy<VM>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}