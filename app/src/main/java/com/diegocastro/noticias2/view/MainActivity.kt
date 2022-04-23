package com.diegocastro.noticias2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.diegocastro.noticias2.databinding.ActivityMainBinding
import com.diegocastro.noticias2.viewmodel.ViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    //llama al view model
    private lateinit var mainViewModel:ViewModel

    //1.
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2
        binding= ActivityMainBinding.inflate(layoutInflater)

        //.3
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        observar()

        //4. trae las noticias de la api al pulsar el boton
        binding.btnTraerNoticias.setOnClickListener {
            binding.progressBar.visibility= View.VISIBLE
            mainViewModel.onBtnTraerNoticias()
        }
    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {
            //aqui va el recycler view
            binding.txtNoticias.text="Noticias: \n"
            binding.txtNoticias.append("${Gson().toJson(it)}")
            binding.progressBar.visibility=View.GONE
        })
    }

}