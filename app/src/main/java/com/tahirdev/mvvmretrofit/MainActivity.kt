package com.tahirdev.mvvmretrofit

import android.app.Application
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tahirdev.mvvmretrofit.api.QuoteService
import com.tahirdev.mvvmretrofit.api.RetrofitHelper
import com.tahirdev.mvvmretrofit.databinding.ActivityMainBinding
import com.tahirdev.mvvmretrofit.models.QuoteList
import com.tahirdev.mvvmretrofit.repository.QuotesRepository
import com.tahirdev.mvvmretrofit.repository.Response
import com.tahirdev.mvvmretrofit.viewmodel.MainViewModel
import com.tahirdev.mvvmretrofit.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.bind(layoutInflater.inflate(R.layout.activity_main, null))
        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = (application as QuoteApplications).repository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        val observer = Observer<Response<QuoteList>> {
            when (it) {
                is Response.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }

                is Response.Loading -> {
                    // Handle loading state if needed
                }

                is Response.Success -> {
                    it.data?.let { data ->
                        binding.quoteText.text = data.results.getOrNull(index)?.content
                        binding.quoteAuthor.text = data.results[index].author
                        Toast.makeText(this, data.results.size.toString(), Toast.LENGTH_LONG).show()
                        Log.d("TahirDev", data.toString())
                    }
                }
            }
        }

        mainViewModel.quotes.observe(this, observer)

        binding.nextBtn.setOnClickListener {
            mainViewModel.quotes.value?.let { response ->
                if (response is Response.Success) {
                    response.data?.let { data ->
                        if (index < data.results.size - 1) {
                            index++
                            binding.quoteText.text = data.results[index].content
                            binding.quoteAuthor.text = data.results[index].author
                        } else {
                            Toast.makeText(this, "No more quotes", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.prevBtn.setOnClickListener() {
            mainViewModel.quotes.value?.let { response ->
                if (response is Response.Success) {
                    response.data?.let { data ->
                            if (index > 0) {
                                index--
                                binding.quoteText.text = data.results[index].content
                                binding.quoteAuthor.text = data.results[index].author
                            } else {
                                Toast.makeText(this, "No more quotes", Toast.LENGTH_SHORT).show()
                            }

                    }
                }
            }
        }
    }


}