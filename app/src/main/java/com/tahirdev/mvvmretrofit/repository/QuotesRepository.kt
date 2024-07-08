package com.tahirdev.mvvmretrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahirdev.mvvmretrofit.api.QuoteService
import com.tahirdev.mvvmretrofit.db.QDatabase
import com.tahirdev.mvvmretrofit.models.QuoteList
import com.tahirdev.mvvmretrofit.utils.NetworkUtil

class QuotesRepository(private val quoteService: QuoteService,
                       private val qDatabase: QDatabase,
                       private val context: Context) {

    private val quoteMLiveData = MutableLiveData<Response<QuoteList>>()
    val quotes : MutableLiveData<Response<QuoteList>>
        get() = quoteMLiveData



    suspend fun getQuotes(page:Int){

        if (NetworkUtil.isOnline(context)){
            try {
                val result = quoteService.getQuotes(page)

                if (result.body() != null){
                    qDatabase.quoteDAO().addQuotes(result.body()!!.results)
                    quoteMLiveData.postValue(Response.Success(result.body()!!))
                } else {
                    quoteMLiveData.postValue(Response.Error("API Error"))
                }
            } catch (e:Exception){
                quoteMLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val quotes = qDatabase.quoteDAO().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes,1,1)
            quoteMLiveData.postValue(Response.Success(quoteList))
        }
    }

    suspend fun getQuotesBackground(){
        val random = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(random)
        if (result.body() != null){
            qDatabase.quoteDAO().addQuotes(result.body()!!.results)
        }
    }


}