package com.diegocastro.noticias2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegocastro.noticias2.repository.interactor.NewsInteractor
import com.diegocastro.noticias2.repository.retrofit.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    //en el view model definimos los parametros que vamos a pasar ej:country

    //la variable noticias es el livedata de clase News
    val noticias: MutableLiveData<News> = MutableLiveData()

    //instanciamos el interactor desde viewmodel
    val interactor=NewsInteractor()

    //creamos la funcion que en el boton click nos trae las noticias
    //llama al interactor y
    //guarda los datos en livedata noticias

    fun onBtnTraerNoticias(){
        //aqui lanzamos la corrutina
        CoroutineScope(Dispatchers.IO).launch {
            noticias.postValue(interactor.traerRespuesta("mx"))
        }

    }
}