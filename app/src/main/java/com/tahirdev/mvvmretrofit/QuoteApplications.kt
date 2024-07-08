package com.tahirdev.mvvmretrofit

import android.annotation.SuppressLint
import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.tahirdev.mvvmretrofit.api.QuoteService
import com.tahirdev.mvvmretrofit.api.RetrofitHelper
import com.tahirdev.mvvmretrofit.db.QDatabase
import com.tahirdev.mvvmretrofit.repository.QuotesRepository
import com.tahirdev.mvvmretrofit.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplications : Application() {

     lateinit var repository : QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }


    private fun setUpWorker(){
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,
            30, TimeUnit.MINUTES).setConstraints(constraints).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {
        val qDatabase = QDatabase.getDatabase(applicationContext)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        repository = QuotesRepository(quoteService,qDatabase,applicationContext)

    }

}