package com.tahirdev.mvvmretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tahirdev.mvvmretrofit.models.QuoteList
import com.tahirdev.mvvmretrofit.repository.QuotesRepository
import com.tahirdev.mvvmretrofit.repository.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepository) : ViewModel() {



    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<Response<QuoteList>>
        get() = repository.quotes

}