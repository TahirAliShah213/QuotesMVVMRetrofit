package com.tahirdev.mvvmretrofit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tahirdev.mvvmretrofit.repository.QuotesRepository

class MainViewModelFactory(val quotesRepository: QuotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quotesRepository) as T
    }
}