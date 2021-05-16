package com.example.vinos_app.entities

class Vino (nombreVino : String, precioVino: Double, ratingVino: Double, bodegaVino: String){

    var nombreVino: String = ""
    var precioVino: Double=0.0
    var ratingVino: Double = 0.0
    var bodegaVino: String = ""

    init{
        this.nombreVino = nombreVino
        this.precioVino = precioVino
        this.ratingVino = ratingVino
        this.bodegaVino = bodegaVino
    }

}