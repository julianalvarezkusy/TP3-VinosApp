package com.example.vinos_app.adapters

import WineViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.vinos_app.R
import com.example.vinos_app.entities.Vino
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class VinoListAdapter(
        val onItemClick: (Int,String) -> Boolean
    ): RecyclerView.Adapter<VinoListAdapter.VinoHolder>() {
    var vinosList: List<Vino> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged() }
    lateinit var vinosListBackup:MutableList<Vino>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VinoHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.wine_list_item, parent,false)
        return (VinoHolder(view))
    }

    fun setData(data: List<Vino>){
        //Log.d("Data","Data: "+data.toString())
        this.vinosList = data
        this.vinosListBackup = data as MutableList<Vino>
    }

    fun filter(text: String) {

        try{
            var filteredList = mutableListOf<Vino>()
            if(text.isEmpty()) {
                filteredList = vinosListBackup
            } else {
                vinosListBackup.forEach {
                    var vino = it.nombre.toLowerCase() + it.bodega.toLowerCase()
                    if(vino.contains(text.toLowerCase()))
                        filteredList.add(it)
                }
            }
            vinosList = filteredList
        }catch (e: Exception){

            Log.d("TAG","Error: "+ e.message)
            Log.d("Error", "VinosListAdapter: "+vinosListBackup.toString())
        }

    }

    override fun onBindViewHolder(holder: VinoHolder, position: Int) {
        holder.setName(vinosList[position].nombre)
        holder.setCellar(vinosList[position].bodega)
        holder.setPrice(vinosList[position].precio.toString())
        holder.setRating(vinosList[position].rating.toString())
        holder.setImage(vinosList[position].nombre)

        holder.getCardLayout().setOnClickListener() {
            //onItemClick(vinosList[position].nombreVino)
            onItemClick(position,"cardView")
        }

        holder.getButtonFav().setOnClickListener(){
            onItemClick(position,"fav")
        }

    }

    override fun getItemCount(): Int {
        return vinosList.size
    }

    class VinoHolder(v:View): RecyclerView.ViewHolder(v){
        private var view:View

        init{
            this.view = v
        }

        fun setName(name: String){
            val txt : TextView = view.findViewById(R.id.wineName)
            txt.text = name
        }

        fun setCellar(name: String){
            val txt : TextView = view.findViewById(R.id.wineBodega)
            txt.text = name
        }

        fun setPrice(name: String){
            val txt: TextView = view.findViewById(R.id.winePrecio)
            txt.text = "$ " + name
        }

        fun setRating (name: String){
            val stars : RatingBar = view.findViewById(R.id.ratingBarCard)
            stars.setRating(name.toFloat())
        }

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardView)
        }

        fun getButtonFav (): ImageView {
            return view.findViewById(R.id.itemFav)
        }

        fun setImage(name: String){
            val wineImage: ImageView = view.findViewById(R.id.wineImage)

        }


    }

}

