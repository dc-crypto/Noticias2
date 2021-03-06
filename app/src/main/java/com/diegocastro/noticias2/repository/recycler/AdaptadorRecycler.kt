package com.diegocastro.noticias2.repository.recycler

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegocastro.noticias2.R
import com.diegocastro.noticias2.repository.retrofit.Article
import com.diegocastro.noticias2.view.DetalleNoticia
import com.diegocastro.noticias2.view.MainActivity
import com.google.gson.Gson

class AdaptadorRecycler(var context: Context,
                        var listDatos: List<Article>,
                        var actividad: MainActivity
) :
    RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null, false)
        return ViewHolderDatos(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {

        holder.itemView.animation =
            AnimationUtils.loadAnimation(context, R.anim.transition)
        holder.titulo.text = Html.fromHtml(listDatos[position].title)
        holder.descripcion.text = Html.fromHtml(listDatos[position].description)

        Glide.with(context)
            .load(listDatos[position].urlToImage)
            .error(R.drawable.sinimagen)
            .into(holder.imagen)

       holder.itemView.setOnClickListener {
            val detalle = Gson().toJson(listDatos[holder.layoutPosition])

            val intent = Intent(actividad, DetalleNoticia::class.java)
            intent.putExtra("data", detalle)
            actividad.startActivity(intent)

           //para compartir las noticias https://developer.android.com/training/sharing/send

        }
        holder.shareBtn.setOnClickListener {

            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey, Check out this news "+ listDatos[0].url)
            intent.type="text/plain"
            context.startActivity(Intent.createChooser(intent,"Share To:"))

        }
    }




    override fun getItemCount(): Int {
        return listDatos.size
    }

    inner class ViewHolderDatos(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        var imagen: ImageView
        var titulo: TextView
        var descripcion: TextView
        val shareBtn: ImageButton

        //contexto
        var con: Context

        init {
            imagen = itemView.findViewById(R.id.myImagen)
            titulo = itemView.findViewById(R.id.myTitulo)
            descripcion = itemView.findViewById(R.id.myDes)
            shareBtn=itemView.findViewById(R.id.share_btn)
            con = context
        }
    }




}