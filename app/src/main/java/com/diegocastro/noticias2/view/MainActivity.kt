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
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    //llama al view model
    private lateinit var mainViewModel:ViewModel

    //1.
    private lateinit var binding : ActivityMainBinding

    //recyclerview
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var adaptador: AdaptadorRecycler

    //shimmer
    private lateinit var shimmerLayout: ShimmerFrameLayout


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

        shimmerLayout = findViewById(R.id.shimmerLayout)

        shimmerLayout.startShimmer()
        mainViewModel.onBtnTraerNoticias()
        myRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


        //4. trae las noticias de la api al pulsar el boton
       /*binding.btnTraerNoticias.setOnClickListener {
            //binding.progressBar.visibility= View.VISIBLE

            mainViewModel.onBtnTraerNoticias()
            shimmerLayout.startShimmer()
            myRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        }*/
    }

    private fun observar() {
        mainViewModel.noticias.observe(this, Observer {
            //aqui va el recycler view
            //binding.txtNoticias.text="Noticias: \n"
            //binding.txtNoticias.append("${Gson().toJson(it)}")
            //binding.progressBar.visibility=View.GONE
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE
            adaptador = AdaptadorRecycler(this, it.articles, this);
            myRecyclerView.adapter = adaptador
        })
    }

}