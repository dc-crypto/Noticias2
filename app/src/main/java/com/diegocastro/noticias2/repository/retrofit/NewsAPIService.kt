package com.diegocastro.noticias2.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=ce48f45f1adc4ebfb78738db027e6239

    @GET("top-headlines?apiKey=ce48f45f1adc4ebfb78738db027e6239")
    fun obtenerNoticias(@Query("country") country: String) : Call<News>
}