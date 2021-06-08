package com.example.vinos_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.vinos_app.R
import com.example.vinos_app.entities.Vino

class VinoListAdapter(
        private var vinosList : MutableList<Vino>,
        val onItemClick: (Int) -> Boolean

        ): RecyclerView.Adapter<VinoListAdapter.VinoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VinoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wine_list_item, parent,false)
        return (VinoHolder(view))
    }

    override fun onBindViewHolder(holder: VinoHolder, position: Int) {
        holder.setName(vinosList[position].nombre)
        holder.setCellar(vinosList[position].bodega)
        holder.setPrice(vinosList[position].precio.toString())
        holder.setRating(vinosList[position].rating.toString())

        holder.getCardLayout().setOnClickListener() {
            //onItemClick(vinosList[position].nombreVino)
            onItemClick(position)
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
            val txt : TextView = view.findViewById(R.id.wineRating)
            txt.text = name + "/5.0"
        }

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardView)
        }

    }








}