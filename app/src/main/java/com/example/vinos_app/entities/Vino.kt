package com.example.vinos_app.entities

import android.os.Parcel
import android.os.Parcelable

class Vino(var nombre: String, var precio: Double, var rating: Double, var bodega: String) {

    constructor() : this("",0.0,0.0,"")

//    init{
//        this.nombre = nombre!!
//        this.precio = precio!!
//        this.rating = rating!!
//        this.bodega = bodega!!
//    }


}
