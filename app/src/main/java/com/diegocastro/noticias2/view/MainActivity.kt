package com.diegocastro.noticias2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegocastro.noticias2.R
import com.diegocastro.noticias2.databinding.ActivityMainBinding
import com.diegocastro.noticias2.repository.recycler.AdaptadorRecycler
import com.diegocastro.noticias2.viewmodel.ViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {



    //llama al view model
    private lateinit var mainViewModel:ViewModel

    //1.
    private lateinit var binding : ActivityMainBinding

    //recyclerview
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var adaptador: AdaptadorRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2
        binding= ActivityMainBinding.inflate(layoutInflater)

        //.3
        setContentView(binding.root)

        //ViewModel
        mainViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        observar()

        //RecyclerView
        myRecyclerView = binding.myRecycler
        //myRecyclerView=findViewById(R.id.myRecycler)
        myRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)


        //4. trae las noticias de la api al pulsar el boton
        binding.btnTraerNoticias.setOnClickListener {
            binding.progressBar.visibility= View.VISIBLE
            mainViewModel.onBtnTraerNoticias()
        }
    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {
            //aqui va el recycler view
            //binding.txtNoticias.text="Noticias: \n"
            //binding.txtNoticias.append("${Gson().toJson(it)}")
            binding.progressBar.visibility=View.GONE

            adaptador = AdaptadorRecycler(this, it.articles, this);
            myRecyclerView.adapter = adaptador
        })
    }

}