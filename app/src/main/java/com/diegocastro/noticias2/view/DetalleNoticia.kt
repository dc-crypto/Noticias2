package com.diegocastro.noticias2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegocastro.noticias2.R
import com.diegocastro.noticias2.databinding.ActivityDetalleNoticiaBinding
import com.diegocastro.noticias2.repository.retrofit.Article
import com.google.gson.Gson

class DetalleNoticia : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleNoticiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleNoticiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val texto = intent.extras?.getString("data")
        val noticia: Article = Gson().fromJson(texto, Article::class.java)

        binding.txtTitulo2.text = noticia.title
        binding.txtNoticia2.text = noticia.description
    }
}