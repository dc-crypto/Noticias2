package com.diegocastro.noticias2.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsAPIService {

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=ce48f45f1adc4ebfb78738db027e6239

    @Headers(
        value=[
            //aqui le pasamos los parámetros de cabecera en este caso son 2
            "X-Api-Key: ce48f45f1adc4ebfb78738db027e6239",
            "content-type:application/json; charset=utf-8"
        ]
    )

    @GET("/v2/top-headlines")
    fun obtenerNoticias(@Query("country") country: String) : Call<News>
}