package com.example.vinos_app.entities

import android.graphics.Bitmap

class Vino(var nombre: String, var precio: Double, var rating: Double, var bodega: String) {

    constructor() : this("",0.0,0.0,"")
    var img :Bitmap?

   init{
    img = null
   }


}
