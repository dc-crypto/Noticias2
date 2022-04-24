package com.diegocastro.noticias2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
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
        val myWebView: WebView = findViewById(R.id.myWebView)
        //binding.txtTitulo2.text = noticia.title
        //binding.txtNoticia2.text = noticia.description

        myWebView.loadUrl(noticia.url)

        val share_btn = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.share_btn)
        share_btn.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey, Check out this news "+noticia.url)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))


        }
    }
}