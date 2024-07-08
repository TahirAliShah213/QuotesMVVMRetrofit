package com.tahirdev.mvvmretrofit.api


import com.tahirdev.mvvmretrofit.models.QuoteList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query(value = "page") page:Int) : Response<QuoteList>

    // BaseUrl + "/quotes" + "?page = 1"

}