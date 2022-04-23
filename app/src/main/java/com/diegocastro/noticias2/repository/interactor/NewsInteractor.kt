package com.diegocastro.noticias2.repository.interactor

import com.diegocastro.noticias2.repository.retrofit.News
import com.diegocastro.noticias2.repository.retrofit.NewsAPIService
import com.diegocastro.noticias2.repository.retrofit.RestEngine
import retrofit2.Call

class NewsInteractor {

    fun traerRespuesta(country:String): News? {
        val llamada: NewsAPIService=
            RestEngine.getRestEngine().create(NewsAPIService::class.java)
        val resultado: Call<News> =llamada.obtenerNoticias(country)
        val p:News?=resultado.execute().body()
        return p

    }
}